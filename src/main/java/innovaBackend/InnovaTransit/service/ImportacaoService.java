package innovaBackend.InnovaTransit.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innovaBackend.InnovaTransit.integracao.IntegracaoService;
import innovaBackend.InnovaTransit.integracao.response.FolhaServicoResponse;
import innovaBackend.InnovaTransit.integracao.response.MotoristaResponse;
import innovaBackend.InnovaTransit.integracao.response.VeiculoResponse;
import innovaBackend.InnovaTransit.model.Empresa;
import innovaBackend.InnovaTransit.model.FolhaServico;
import innovaBackend.InnovaTransit.model.Motorista;
import innovaBackend.InnovaTransit.model.Veiculo;
import innovaBackend.InnovaTransit.repository.EmpresaRepository;
import innovaBackend.InnovaTransit.repository.FolhaServicoRepository;
import innovaBackend.InnovaTransit.repository.MotoristaRepository;
import innovaBackend.InnovaTransit.repository.VeiculoRepository;

@Service
public class ImportacaoService {

    @Autowired
    private IntegracaoService integracaoService;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private FolhaServicoRepository folhaServicoRepository;
    
    @Autowired
    private EmpresaRepository empresaRepository;

    // Método para importar todos os dados
    public void importar(Long empresaId) {
        this.importarMotoristas(empresaId);
        this.importarVeiculos(empresaId);
        this.importarFolhaServico(empresaId);
    }

    // Importação de motoristas com o empresaId
    public void importarMotoristas(Long empresaId) {
        List<MotoristaResponse> motoristas = integracaoService.getDataMotorista(empresaId);

        for (MotoristaResponse motorista : motoristas) {
            // Buscando o motorista pelo matricula e empresaId
            Motorista motoristaExistente = motoristaRepository.findByMatriculaAndEmpresaId(motorista.getMatricula(), empresaId);
            if (motoristaExistente != null) {
                motoristaExistente.setNome(motorista.getNome());
                motoristaRepository.save(motoristaExistente);
                System.out.println("Motorista atualizado: " + motoristaExistente.getMatricula());
            } else {
                Motorista novoMotorista = new Motorista();
                novoMotorista.setMatricula(motorista.getMatricula());
                novoMotorista.setNome(motorista.getNome());
                novoMotorista.setEmail(motorista.getEmail());
                
                // Buscando a empresa pelo id e associando ao motorista
                Empresa empresa = empresaRepository.findById(empresaId)
                        .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
                novoMotorista.setEmpresa(empresa);  // Associando a empresa ao motorista
                
                motoristaRepository.save(novoMotorista);
                System.out.println("Novo motorista criado: " + novoMotorista.getMatricula());
            }
        }
    }

    // Importação de veículos com o empresaId
    public void importarVeiculos(Long empresaId) {
        // Obtendo a lista de veículos da API externa
        List<VeiculoResponse> veiculos = integracaoService.getDataVeiculo(empresaId);

        for (VeiculoResponse veiculo : veiculos) {
            // Buscando o veículo pelo numeroVeiculo e empresaId
            Veiculo veiculoExistente = veiculoRepository.findByNumeroVeiculoAndEmpresaId(veiculo.getNumeroVeiculo(), empresaId);
            
            if (veiculoExistente != null) {
                // Atualizando os dados do veículo existente
                veiculoExistente.setHodometro(veiculo.getHodometro());
                veiculoExistente.setCatraca(veiculo.getCatraca());
                veiculoRepository.save(veiculoExistente);
            } else {
                // Criando um novo veículo
                Veiculo novoVeiculo = new Veiculo();
                novoVeiculo.setNumeroVeiculo(veiculo.getNumeroVeiculo());
                novoVeiculo.setHodometro(veiculo.getHodometro());
                novoVeiculo.setCatraca(veiculo.getCatraca());

                // Buscando a empresa pelo id e associando ao veículo
                Empresa empresa = empresaRepository.findById(empresaId)
                        .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
                novoVeiculo.setEmpresa(empresa);  // Associando a empresa ao veículo

                // Salvando o novo veículo
                veiculoRepository.save(novoVeiculo);
            }
        }
    }
    
    public void importarFolhaServico(Long empresaId) {
        // Buscando as folhas de serviço já existentes para a data e empresa
        List<FolhaServico> folhasServicosExistentes = folhaServicoRepository.findByDataServicoAndEmpresaId(LocalDate.now(), empresaId);

        // Verificando se já existem folhas de serviço para a data de hoje
        if (folhasServicosExistentes != null && !folhasServicosExistentes.isEmpty()) {
            // Caso as folhas já existam, apenas exibe uma mensagem e não realiza mais importação
            System.out.println("Folhas de serviço já existem para a data " + LocalDate.now() + ". Nenhuma nova folha será importada.");
            return;  // Interrompe o processamento, não prosseguindo com a importação
        }

        // Se não houver folhas de serviço existentes, então prossegue com a importação
        List<FolhaServicoResponse> folhasServicosExternas = integracaoService.getDataFolhaServico(empresaId);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (FolhaServicoResponse folhaServicoResponse : folhasServicosExternas) {
            FolhaServico novaFolhaServico = new FolhaServico();
            // Associando motoristas e veículos com empresaId
            novaFolhaServico.setMotorista(motoristaRepository.findByMatriculaAndEmpresaId(folhaServicoResponse.getMotorista(), empresaId));
            novaFolhaServico.setVeiculo(veiculoRepository.findByNumeroVeiculoAndEmpresaId(folhaServicoResponse.getVeiculo(), empresaId));
            LocalTime horarioInicio = LocalTime.parse(folhaServicoResponse.getHorarioInicial(), formatter);
            novaFolhaServico.setHorarioInicial(horarioInicio);
            LocalTime horarioFim = LocalTime.parse(folhaServicoResponse.getHorarioFinal(), formatter);
            novaFolhaServico.setHorarioFinal(horarioFim);
            novaFolhaServico.setDataServico(LocalDate.now());

            // Buscando a empresa pelo id e associando à folha de serviço
            Empresa empresa = empresaRepository.findById(empresaId)
                    .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
            novaFolhaServico.setEmpresa(empresa);  // Associando a empresa à folha de serviço

            novaFolhaServico.montarTarefas(folhaServicoResponse.getTarefas());
            folhaServicoRepository.save(novaFolhaServico);
        }

        System.out.println("Folhas de serviço importadas da API externa e salvas no banco.");
    }


}
