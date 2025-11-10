package main.frontend.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import main.frontend.service.ApiService;
import main.model.*;
import main.enums.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class MainController implements Initializable {

    // Serviço para API
    private ApiService apiService;
    
    // Componentes da interface - Dados da Criança
    @FXML private TextField txtNomeCrianca;
    @FXML private DatePicker dpDataNascimento;
    @FXML private TextField txtRgCrianca;
    @FXML private TextField txtCpfCrianca;
    @FXML private ComboBox<Sexo> cbSexo;
    @FXML private ComboBox<Raca> cbRaca;
    @FXML private ComboBox<SimNao> cbGemeo;
    @FXML private ComboBox<SimNao> cbPossuiIrmaoNaCreche;
    @FXML private TextField txtCadastroNacionalSus;
    @FXML private TextField txtUnidadeSaude;
    @FXML private ComboBox<SimNao> cbProblemasSaude;
    @FXML private TextField txtMunicipioNascimento;
    @FXML private TextField txtCartorioRegistro;
    @FXML private TextField txtMunicipioRegistro;
    @FXML private DatePicker dpDataEmissaoRg;
    @FXML private TextField txtOrgaoEmissorRg;
    
    // Componentes da interface - Dados do Responsável
    @FXML private TextField txtNomeResponsavel;
    @FXML private TextField txtRgResponsavel;
    @FXML private TextField txtCpfResponsavel;
    @FXML private ComboBox<Parentesco> cbParentesco;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtWhatsapp;
    @FXML private TextField txtEmail;
    @FXML private DatePicker dpDataNascimentoResponsavel;
    @FXML private TextField txtMunicipioNascimentoResponsavel;
    @FXML private TextField txtEscolaridade;
    @FXML private TextField txtProfissao;
    @FXML private TextField txtRendaFamiliar;
    
        // Componentes da interface - Endereço
    @FXML private TextField txtLogradouro;
    @FXML private TextField txtNumero;
    @FXML private TextField txtComplemento;
    @FXML private TextField txtBairro;
    @FXML private TextField txtCidade;
    @FXML private TextField txtCep;
    @FXML private TextField txtUf;
    @FXML private TextField txtEndereco;
    @FXML private TextField txtMunicipio;
    @FXML private ComboBox<TipoMoradia> cbTipoMoradia;
    @FXML private ComboBox<TipoPiso> cbTipoPiso;
    
    // Móveis do Domicílio - checkboxes para múltipla seleção
    @FXML private CheckBox chkMovelTV;
    @FXML private CheckBox chkMovelDVD;
    @FXML private CheckBox chkMovelRadio;
    @FXML private CheckBox chkMovelComputador;
    @FXML private CheckBox chkMovelNotebook;
    @FXML private CheckBox chkMovelTelefoneFixo;
    @FXML private CheckBox chkMovelTelefoneCelular;
    @FXML private CheckBox chkMovelTablet;
    @FXML private CheckBox chkMovelInternet;
    @FXML private CheckBox chkMovelTVAssinatura;
    @FXML private CheckBox chkMovelFogao;
    @FXML private CheckBox chkMovelGeladeira;
    @FXML private CheckBox chkMovelFreezer;
    @FXML private CheckBox chkMovelMicroOndas;
    @FXML private CheckBox chkMovelMaquinaLavar;
    @FXML private CheckBox chkMovelArCondicionado;
    @FXML private CheckBox chkMovelBicicleta;
    @FXML private CheckBox chkMovelMoto;
    @FXML private CheckBox chkMovelAutomovel;
    @FXML private ComboBox<SituacaoCasa> cbSituacaoCasa;
    @FXML private TextField txtValorAluguel;
    @FXML private TextField txtNumeroComodos;
    
    // Componentes da interface - Informações Sociais e de Saúde
    @FXML private CheckBox chkRestricaoAlimentarLactose;
    @FXML private CheckBox chkRestricaoAlimentarGluten;
    @FXML private CheckBox chkRestricaoAlimentarAcucar;
    @FXML private CheckBox chkRestricaoAlimentarSal;
    @FXML private CheckBox chkRestricaoAlimentarCorante;
    @FXML private CheckBox chkAlergiaPoeira;
    @FXML private CheckBox chkAlergiaMedicamentos;
    @FXML private CheckBox chkAlergiaAlimentos;
    @FXML private CheckBox chkAlergiaAnimais;
    @FXML private CheckBox chkAlergiaCosmeticos;
    @FXML private CheckBox chkMobilidadeCadeirante;
    @FXML private CheckBox chkMobilidadeMuletas;
    @FXML private CheckBox chkMobilidadeProtese;
    @FXML private CheckBox chkMobilidadeBengala;
    // Educação Especial - campos expandidos
    @FXML private VBox vboxEducacaoEspecial;
    
    // Auxílios - seção condicional
    @FXML private VBox vboxAuxilios;
    @FXML private CheckBox chkAltasHabilidades;
    @FXML private CheckBox chkCegueira;
    @FXML private CheckBox chkDeficienciaAuditivaLeve;
    @FXML private CheckBox chkDeficienciaAuditivaSevera;
    @FXML private CheckBox chkDeficienciaAuditivaProcessamento;
    @FXML private CheckBox chkDeficienciaVisualBaixa;
    @FXML private CheckBox chkDeficienciaFisicaCadeirante;
    @FXML private CheckBox chkDeficienciaFisicaParalisia;
    @FXML private CheckBox chkDeficienciaFisicaParaplegia;
    @FXML private CheckBox chkDeficienciaFisicaOutros;
    @FXML private CheckBox chkDisfemiaGagueira;
    @FXML private CheckBox chkDeficienciaIntelectual;
    @FXML private CheckBox chkSensorialAltaSensibilidade;
    @FXML private CheckBox chkSensorialBaixaSensibilidade;
    @FXML private CheckBox chkDeficienciaMental;
    @FXML private CheckBox chkEspectroAutistaNivel1;
    @FXML private CheckBox chkEspectroAutistaNivel2;
    @FXML private CheckBox chkEspectroAutistaNivel3;
    @FXML private CheckBox chkEstrabismo;
    @FXML private CheckBox chkSurdez;
    @FXML private CheckBox chkSindromeDown;
    @FXML private CheckBox chkTEA;
    @FXML private CheckBox chkTDAH;
    @FXML private CheckBox chkTOD;
    @FXML private ComboBox<SimNao> cbRecebeBeneficioSocial;
    @FXML private CheckBox chkAuxilioEmergencial;
    @FXML private CheckBox chkAuxilioBrasil;
    @FXML private CheckBox chkSeguroDesemprego;
    @FXML private CheckBox chkBeneficioAssistencial;
    @FXML private CheckBox chkAuxilioMaternidade;
    @FXML private CheckBox chkPensaoMorte;
    @FXML private CheckBox chkOutrosAuxilios;
    @FXML private TextField txtNis;
    
    // Pessoas Autorizadas
    @FXML private TextField txtNomePessoa1;
    @FXML private ComboBox<Parentesco> cbParentescoPessoa1;
    @FXML private TextField txtNomePessoa2;
    @FXML private ComboBox<Parentesco> cbParentescoPessoa2;
    @FXML private TextField txtNomePessoa3;
    @FXML private ComboBox<Parentesco> cbParentescoPessoa3;
    
    // TabPane
    @FXML private TabPane tabPane;
    @FXML private Tab tabPrincipal;
    
    // Botões
    @FXML private Button btnSalvar;
    @FXML private Button btnLimpar;
    @FXML private Button btnBuscar;
    @FXML private Button btnEditar;
    
    // Status do formulário
    @FXML private ComboBox<Status> cbStatusFormulario;
    
    // Campos de busca
    @FXML private TextField txtCpfBusca;
    @FXML private ComboBox<Status> cbStatusBusca;
    @FXML private Button btnBuscarPorStatus;
    
    // Tabela de formulários
    @FXML private TableView<FormularioCompleto> tblFormularios;
    @FXML private TableColumn<FormularioCompleto, String> colNomeCrianca;
    @FXML private TableColumn<FormularioCompleto, LocalDate> colDataNascimento;
    @FXML private TableColumn<FormularioCompleto, String> colNomeResponsavel;
    
    // Lista observável para a tabela
    private ObservableList<FormularioCompleto> formularios;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        apiService = new ApiService();
        inicializarComponentes();
        configurarTabela();
        // carregarFormularios(); // Removido para evitar erro na inicialização
    }
    
    private void inicializarComponentes() {
        // Inicializar ComboBoxes básicos
        cbSexo.setItems(FXCollections.observableArrayList(Sexo.values()));
        cbRaca.setItems(FXCollections.observableArrayList(Raca.values()));
        
        // Configurar ComboBoxes novos - Dados da Criança
        if (cbGemeo != null) cbGemeo.setItems(FXCollections.observableArrayList(SimNao.values()));
        if (cbPossuiIrmaoNaCreche != null) cbPossuiIrmaoNaCreche.setItems(FXCollections.observableArrayList(SimNao.values()));
        if (cbProblemasSaude != null) cbProblemasSaude.setItems(FXCollections.observableArrayList(SimNao.values()));
        
        // Configurar ComboBoxes - Responsável
        if (cbParentesco != null) cbParentesco.setItems(FXCollections.observableArrayList(Parentesco.values()));
        
        // Configurar ComboBoxes - Endereço/Moradia
        if (cbTipoMoradia != null) cbTipoMoradia.setItems(FXCollections.observableArrayList(TipoMoradia.values()));
        if (cbTipoPiso != null) cbTipoPiso.setItems(FXCollections.observableArrayList(TipoPiso.values()));
        if (cbSituacaoCasa != null) cbSituacaoCasa.setItems(FXCollections.observableArrayList(SituacaoCasa.values()));
        
        // Configurar ComboBoxes - Benefícios
        if (cbRecebeBeneficioSocial != null) cbRecebeBeneficioSocial.setItems(FXCollections.observableArrayList(SimNao.values()));
        
        // Configurar ComboBoxes - Pessoas Autorizadas
        if (cbParentescoPessoa1 != null) cbParentescoPessoa1.setItems(FXCollections.observableArrayList(Parentesco.values()));
        if (cbParentescoPessoa2 != null) cbParentescoPessoa2.setItems(FXCollections.observableArrayList(Parentesco.values()));
        if (cbParentescoPessoa3 != null) cbParentescoPessoa3.setItems(FXCollections.observableArrayList(Parentesco.values()));
        
        // Configurar ComboBox - Status para formulário (definir PENDENTE como padrão)
        if (cbStatusFormulario != null) {
            cbStatusFormulario.setItems(FXCollections.observableArrayList(Status.values()));
            cbStatusFormulario.setValue(Status.PENDENTE); // Padrão PENDENTE
        }
        
        // Configurar ComboBox - Status para busca
        if (cbStatusBusca != null) cbStatusBusca.setItems(FXCollections.observableArrayList(Status.values()));
        
        // Configurar UF padrão
        txtUf.setText("MA");
        
        // Configurar eventos dos botões
        btnSalvar.setOnAction(e -> salvarFormulario());
        btnLimpar.setOnAction(e -> limparCampos());
        btnBuscar.setOnAction(e -> buscarPorCpf());
        btnEditar.setOnAction(e -> editarPorCpf());
        if (btnBuscarPorStatus != null) btnBuscarPorStatus.setOnAction(e -> buscarPorStatus());
        
        // Configurar listener para mostrar/ocultar educação especial
        if (cbProblemasSaude != null && vboxEducacaoEspecial != null) {
            cbProblemasSaude.valueProperty().addListener((observable, oldValue, newValue) -> {
                boolean mostrarEducacaoEspecial = newValue == SimNao.SIM;
                vboxEducacaoEspecial.setVisible(mostrarEducacaoEspecial);
                vboxEducacaoEspecial.setManaged(mostrarEducacaoEspecial);
                
                // Se ocultar a seção, limpar todas as seleções
                if (!mostrarEducacaoEspecial) {
                    limparCamposEducacaoEspecial();
                }
            });
        }
        
        // Configurar listener para mostrar/ocultar auxílios
        if (cbRecebeBeneficioSocial != null && vboxAuxilios != null) {
            cbRecebeBeneficioSocial.valueProperty().addListener((observable, oldValue, newValue) -> {
                boolean mostrarAuxilios = newValue == SimNao.SIM;
                vboxAuxilios.setVisible(mostrarAuxilios);
                vboxAuxilios.setManaged(mostrarAuxilios);
                
                // Se ocultar a seção, limpar todas as seleções
                if (!mostrarAuxilios) {
                    limparCamposAuxilios();
                }
            });
        }
    }
    
    private void configurarTabela() {
        // Configurar colunas da tabela
        colNomeCrianca.setCellValueFactory(new PropertyValueFactory<>("nomeCrianca"));
        colDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        colNomeResponsavel.setCellValueFactory(cellData -> {
            Set<Responsavel> responsaveis = cellData.getValue().getResponsavel();
            if (responsaveis != null && !responsaveis.isEmpty()) {
                return new javafx.beans.property.SimpleStringProperty(
                    responsaveis.iterator().next().getNome()
                );
            }
            return new javafx.beans.property.SimpleStringProperty("");
        });
        
        // Inicializar lista observável
        formularios = FXCollections.observableArrayList();
        tblFormularios.setItems(formularios);
    }
    
    private void salvarFormulario() {
        try {
            // Validar campos obrigatórios
            if (!validarCamposObrigatorios()) {
                return;
            }
            
            // Criar objeto FormularioCompleto
            FormularioCompleto formulario = new FormularioCompleto();
            
            // Dados da criança - APENAS CAMPOS OBRIGATÓRIOS E EXISTENTES
            formulario.setNomeCrianca(txtNomeCrianca.getText().trim());
            formulario.setDataNascimento(dpDataNascimento.getValue());
            
            // Campos opcionais apenas se existirem no FXML
            if (txtRgCrianca != null) {
                formulario.setRg(txtRgCrianca.getText().trim().isEmpty() ? null : txtRgCrianca.getText().trim());
            }
            if (txtCpfCrianca != null) {
                formulario.setCpf(txtCpfCrianca.getText().trim());
            }
            if (cbSexo != null) {
                formulario.setSexo(cbSexo.getValue());
            }
            if (cbRaca != null) {
                formulario.setRaca(cbRaca.getValue());
            }
            if (cbGemeo != null) {
                formulario.setGemeo(cbGemeo.getValue());
            }
            if (cbPossuiIrmaoNaCreche != null) {
                formulario.setPossuiIrmaoNaCreche(cbPossuiIrmaoNaCreche.getValue());
            }
            if (txtCadastroNacionalSus != null) {
                formulario.setCadastroNacionalSus(txtCadastroNacionalSus.getText().trim());
            }
            if (txtUnidadeSaude != null) {
                formulario.setUnidadeSaude(txtUnidadeSaude.getText().trim());
            }
            if (cbProblemasSaude != null) {
                formulario.setProblemasSaude(cbProblemasSaude.getValue());
            }
            if (txtMunicipioNascimento != null) {
                formulario.setMunicipioNascimento(txtMunicipioNascimento.getText().trim());
            } else {
                formulario.setMunicipioNascimento("São Luís"); // valor padrão
            }
            if (txtCartorioRegistro != null) {
                formulario.setCartorioRegistro(txtCartorioRegistro.getText().trim());
            }
            if (txtMunicipioRegistro != null) {
                formulario.setMunicipioRegistro(txtMunicipioRegistro.getText().trim());
            }
            if (dpDataEmissaoRg != null) {
                formulario.setDataEmissaoRg(dpDataEmissaoRg.getValue());
            }
            if (txtOrgaoEmissorRg != null) {
                formulario.setOrgaoEmissorRg(txtOrgaoEmissorRg.getText().trim());
            }
            formulario.setDataMatricula(LocalDate.now());
            
            // Configurar campos obrigatórios com valores padrão
            formulario.setNumeroComodos(1); // valor padrão
            formulario.setSerieQueIraCursar(1); // valor padrão
            formulario.setAnoLetivo(2025); // ano atual
            
            // Configurar enums collections para restrições alimentares
            Set<RestricaoAlimentar> restricoes = new HashSet<>();
            if (chkRestricaoAlimentarLactose != null && chkRestricaoAlimentarLactose.isSelected()) restricoes.add(RestricaoAlimentar.LACTOSE);
            if (chkRestricaoAlimentarGluten != null && chkRestricaoAlimentarGluten.isSelected()) restricoes.add(RestricaoAlimentar.GLUTEN);
            if (chkRestricaoAlimentarAcucar != null && chkRestricaoAlimentarAcucar.isSelected()) restricoes.add(RestricaoAlimentar.OUTROS);
            if (chkRestricaoAlimentarSal != null && chkRestricaoAlimentarSal.isSelected()) restricoes.add(RestricaoAlimentar.OUTROS);
            if (chkRestricaoAlimentarCorante != null && chkRestricaoAlimentarCorante.isSelected()) restricoes.add(RestricaoAlimentar.OUTROS);
            formulario.setRestricaoAlimentar(restricoes);
            
            // Configurar alergias
            Set<Alergias> alergias = new HashSet<>();
            if (chkAlergiaPoeira != null && chkAlergiaPoeira.isSelected()) alergias.add(Alergias.ACAROS);
            if (chkAlergiaMedicamentos != null && chkAlergiaMedicamentos.isSelected()) alergias.add(Alergias.PICADA_INSETO);
            if (chkAlergiaAlimentos != null && chkAlergiaAlimentos.isSelected()) alergias.add(Alergias.LEITE);
            if (chkAlergiaAnimais != null && chkAlergiaAnimais.isSelected()) alergias.add(Alergias.CABELOS_DE_ANIMAIS);
            if (chkAlergiaCosmeticos != null && chkAlergiaCosmeticos.isSelected()) alergias.add(Alergias.POLEN);
            formulario.setAlergia(alergias);
            
            // Configurar mobilidade - apenas um valor
            if (chkMobilidadeCadeirante != null && chkMobilidadeCadeirante.isSelected()) {
                formulario.setMobilidade(Mobilidade.PERMANENTE);
            } else if (chkMobilidadeMuletas != null && chkMobilidadeMuletas.isSelected()) {
                formulario.setMobilidade(Mobilidade.TEMPORARIA);
            }
            
            // Configurar educação especial - todos os campos
            Set<EducacaoEspecial> educacaoEspeciais = new HashSet<>();
            if (chkAltasHabilidades != null && chkAltasHabilidades.isSelected()) educacaoEspeciais.add(EducacaoEspecial.ALTAS_HABILIDADES_SUPERDOTACAO);
            if (chkCegueira != null && chkCegueira.isSelected()) educacaoEspeciais.add(EducacaoEspecial.CEGUEIRA);
            if (chkDeficienciaAuditivaLeve != null && chkDeficienciaAuditivaLeve.isSelected()) educacaoEspeciais.add(EducacaoEspecial.DEFICIENCIA_AUDITIVA_LEVE_OU_MODERADA);
            if (chkDeficienciaAuditivaSevera != null && chkDeficienciaAuditivaSevera.isSelected()) educacaoEspeciais.add(EducacaoEspecial.DEFICIENCIA_AUDITIVA_SEVERA_OU_PROFUNDA);
            if (chkDeficienciaAuditivaProcessamento != null && chkDeficienciaAuditivaProcessamento.isSelected()) educacaoEspeciais.add(EducacaoEspecial.DEFICIENCIA_AUDITIVA_PROCESSAMENTO_CENTRAL);
            if (chkDeficienciaVisualBaixa != null && chkDeficienciaVisualBaixa.isSelected()) educacaoEspeciais.add(EducacaoEspecial.DEFICIENCIA_VISUAL_BAIXA_VISAO);
            if (chkDeficienciaFisicaCadeirante != null && chkDeficienciaFisicaCadeirante.isSelected()) educacaoEspeciais.add(EducacaoEspecial.DEFICIENCIA_FISICA_CADEIRANTE);
            if (chkDeficienciaFisicaParalisia != null && chkDeficienciaFisicaParalisia.isSelected()) educacaoEspeciais.add(EducacaoEspecial.DEFICIENCIA_FISICA_PARALISIA_CEREBRAL);
            if (chkDeficienciaFisicaParaplegia != null && chkDeficienciaFisicaParaplegia.isSelected()) educacaoEspeciais.add(EducacaoEspecial.DEFICIENCIA_FISICA_PARAPLEGIA_OU_MONOPLEGA);
            if (chkDeficienciaFisicaOutros != null && chkDeficienciaFisicaOutros.isSelected()) educacaoEspeciais.add(EducacaoEspecial.DEFICIENCIA_FISICA_OUTROS);
            if (chkDisfemiaGagueira != null && chkDisfemiaGagueira.isSelected()) educacaoEspeciais.add(EducacaoEspecial.DISFEMIA_GAGUEIRA);
            if (chkDeficienciaIntelectual != null && chkDeficienciaIntelectual.isSelected()) educacaoEspeciais.add(EducacaoEspecial.DEFICIENCIA_INTELECTUAL);
            if (chkSensorialAltaSensibilidade != null && chkSensorialAltaSensibilidade.isSelected()) educacaoEspeciais.add(EducacaoEspecial.SENSORIAL_ALTA_SENSIBILIDADE);
            if (chkSensorialBaixaSensibilidade != null && chkSensorialBaixaSensibilidade.isSelected()) educacaoEspeciais.add(EducacaoEspecial.SENSORIAL_BAIXA_SENSIBILIDADE);
            if (chkDeficienciaMental != null && chkDeficienciaMental.isSelected()) educacaoEspeciais.add(EducacaoEspecial.DEFICIENCIA_MENTAL);
            if (chkEspectroAutistaNivel1 != null && chkEspectroAutistaNivel1.isSelected()) educacaoEspeciais.add(EducacaoEspecial.ESPECTRO_AUTISTA_NIVEL_I);
            if (chkEspectroAutistaNivel2 != null && chkEspectroAutistaNivel2.isSelected()) educacaoEspeciais.add(EducacaoEspecial.ESPECTRO_AUTISTA_NIVEL_II);
            if (chkEspectroAutistaNivel3 != null && chkEspectroAutistaNivel3.isSelected()) educacaoEspeciais.add(EducacaoEspecial.ESPECTRO_AUTISTA_NIVEL_III);
            if (chkEstrabismo != null && chkEstrabismo.isSelected()) educacaoEspeciais.add(EducacaoEspecial.ESTRABISMO);
            if (chkSurdez != null && chkSurdez.isSelected()) educacaoEspeciais.add(EducacaoEspecial.SURDEZ);
            if (chkSindromeDown != null && chkSindromeDown.isSelected()) educacaoEspeciais.add(EducacaoEspecial.SINDROME_DE_DOWN);
            if (chkTEA != null && chkTEA.isSelected()) educacaoEspeciais.add(EducacaoEspecial.TEA);
            if (chkTDAH != null && chkTDAH.isSelected()) educacaoEspeciais.add(EducacaoEspecial.TDAH);
            if (chkTOD != null && chkTOD.isSelected()) educacaoEspeciais.add(EducacaoEspecial.TOD);
            formulario.setEducacaoEspecial(educacaoEspeciais);
            
            // Configurar benefícios sociais
            formulario.setRecebeBeneficioSocial(cbRecebeBeneficioSocial != null ? cbRecebeBeneficioSocial.getValue() : null);
            
            // Configurar auxílios
            Set<Auxilio> auxilios = new HashSet<>();
            if (chkAuxilioEmergencial != null && chkAuxilioEmergencial.isSelected()) auxilios.add(Auxilio.AUXILIO_EMERGENCIAL);
            if (chkAuxilioBrasil != null && chkAuxilioBrasil.isSelected()) auxilios.add(Auxilio.AUXILIO_BRASIL);
            if (chkSeguroDesemprego != null && chkSeguroDesemprego.isSelected()) auxilios.add(Auxilio.SEGURO_DESEMPREGO);
            if (chkBeneficioAssistencial != null && chkBeneficioAssistencial.isSelected()) auxilios.add(Auxilio.BENEFICIO_PRESTACAO_CONTINUADA);
            if (chkAuxilioMaternidade != null && chkAuxilioMaternidade.isSelected()) auxilios.add(Auxilio.AUXILIO_ALIMENTACAO);
            if (chkPensaoMorte != null && chkPensaoMorte.isSelected()) auxilios.add(Auxilio.VALE_GAS);
            if (chkOutrosAuxilios != null && chkOutrosAuxilios.isSelected()) auxilios.add(Auxilio.AUXILIO_CRECHE);
            formulario.setAuxilios(auxilios);
            
            formulario.setNis(txtNis != null && !txtNis.getText().trim().isEmpty() ? txtNis.getText().trim() : null);
            
            // Criar endereço
            Endereco endereco = new Endereco();
            endereco.setEndereco(txtEndereco.getText().trim());
            endereco.setNumero(txtNumero.getText().trim().isEmpty() ? null : txtNumero.getText().trim());
            endereco.setBairro(txtBairro.getText().trim());
            endereco.setMunicipio(txtMunicipio.getText().trim());
            endereco.setCep(txtCep.getText().trim().isEmpty() ? null : txtCep.getText().trim());
            endereco.setUF(txtUf.getText().trim());
            
            // Configurar móveis do domicílio
            Set<Moveis> moveis = new HashSet<>();
            if (chkMovelTV != null && chkMovelTV.isSelected()) moveis.add(Moveis.TV);
            if (chkMovelDVD != null && chkMovelDVD.isSelected()) moveis.add(Moveis.DVD);
            if (chkMovelRadio != null && chkMovelRadio.isSelected()) moveis.add(Moveis.RÁDIO);
            if (chkMovelComputador != null && chkMovelComputador.isSelected()) moveis.add(Moveis.COMPUTADOR);
            if (chkMovelNotebook != null && chkMovelNotebook.isSelected()) moveis.add(Moveis.NOTEBOOK);
            if (chkMovelTelefoneFixo != null && chkMovelTelefoneFixo.isSelected()) moveis.add(Moveis.TELEFONE_FIXO);
            if (chkMovelTelefoneCelular != null && chkMovelTelefoneCelular.isSelected()) moveis.add(Moveis.TELEFONE_CELULAR);
            if (chkMovelTablet != null && chkMovelTablet.isSelected()) moveis.add(Moveis.TABLET);
            if (chkMovelInternet != null && chkMovelInternet.isSelected()) moveis.add(Moveis.INTERNET);
            if (chkMovelTVAssinatura != null && chkMovelTVAssinatura.isSelected()) moveis.add(Moveis.TV_ASSINATURA);
            if (chkMovelFogao != null && chkMovelFogao.isSelected()) moveis.add(Moveis.FOGÃO);
            if (chkMovelGeladeira != null && chkMovelGeladeira.isSelected()) moveis.add(Moveis.GELADEIRA);
            if (chkMovelFreezer != null && chkMovelFreezer.isSelected()) moveis.add(Moveis.FREEZER);
            if (chkMovelMicroOndas != null && chkMovelMicroOndas.isSelected()) moveis.add(Moveis.MICRO_ONDAS);
            if (chkMovelMaquinaLavar != null && chkMovelMaquinaLavar.isSelected()) moveis.add(Moveis.MÁQUINA_DE_LAVAR_ROUPA);
            if (chkMovelArCondicionado != null && chkMovelArCondicionado.isSelected()) moveis.add(Moveis.AR_CONDICIONADO);
            if (chkMovelBicicleta != null && chkMovelBicicleta.isSelected()) moveis.add(Moveis.BICICLETA);
            if (chkMovelMoto != null && chkMovelMoto.isSelected()) moveis.add(Moveis.MOTO);
            if (chkMovelAutomovel != null && chkMovelAutomovel.isSelected()) moveis.add(Moveis.AUTOMÓVEL);
            formulario.setNoSeuDomicilioTem(moveis);
            
            // Criar responsável
            Responsavel responsavel = new Responsavel();
            responsavel.setNome(txtNomeResponsavel.getText().trim());
            responsavel.setCpf(txtCpfResponsavel.getText().trim());
            responsavel.setRg(txtRgResponsavel.getText().trim());
            responsavel.setTelefone(txtTelefone.getText().trim());
            responsavel.setEndereco(endereco);
            // Configurar renda familiar
            try {
                formulario.setRendaFamiliarMensal(txtRendaFamiliar != null && !txtRendaFamiliar.getText().trim().isEmpty() ? 
                    new java.math.BigDecimal(txtRendaFamiliar.getText().trim()) : null);
            } catch (NumberFormatException e) {
                formulario.setRendaFamiliarMensal(null);
            }
            
            // Adicionar responsável ao formulário
            Set<Responsavel> responsaveis = new HashSet<>();
            responsaveis.add(responsavel);
            formulario.setResponsavel(responsaveis);
            
            // Configurar pessoas autorizadas
            Set<PessoasAutorizadas> pessoasAutorizadas = new HashSet<>();
            
            // Pessoa 1
            if (txtNomePessoa1 != null && !txtNomePessoa1.getText().trim().isEmpty()) {
                PessoasAutorizadas pessoa1 = new PessoasAutorizadas();
                pessoa1.setNomeCompleto(txtNomePessoa1.getText().trim());
                pessoa1.setParentesco(cbParentescoPessoa1 != null ? cbParentescoPessoa1.getValue() : null);
                pessoasAutorizadas.add(pessoa1);
            }
            
            // Pessoa 2
            if (txtNomePessoa2 != null && !txtNomePessoa2.getText().trim().isEmpty()) {
                PessoasAutorizadas pessoa2 = new PessoasAutorizadas();
                pessoa2.setNomeCompleto(txtNomePessoa2.getText().trim());
                pessoa2.setParentesco(cbParentescoPessoa2 != null ? cbParentescoPessoa2.getValue() : null);
                pessoasAutorizadas.add(pessoa2);
            }
            
            // Pessoa 3
            if (txtNomePessoa3 != null && !txtNomePessoa3.getText().trim().isEmpty()) {
                PessoasAutorizadas pessoa3 = new PessoasAutorizadas();
                pessoa3.setNomeCompleto(txtNomePessoa3.getText().trim());
                pessoa3.setParentesco(cbParentescoPessoa3 != null ? cbParentescoPessoa3.getValue() : null);
                pessoasAutorizadas.add(pessoa3);
            }
            
            formulario.setPessoasAutorizadas(pessoasAutorizadas);
            
            // Definir status selecionado pelo usuário
            if (cbStatusFormulario != null && cbStatusFormulario.getValue() != null) {
                formulario.setStatus(cbStatusFormulario.getValue());
            } else {
                formulario.setStatus(Status.PENDENTE); // Fallback para PENDENTE
            }
            
            // Salvar via API
            apiService.salvarFormulario(formulario);
            
            // Mostrar sucesso
            mostrarAlerta("Sucesso", "Formulário salvo com sucesso!", Alert.AlertType.INFORMATION);
            
            // Limpar campos e recarregar tabela
            limparCampos();
            carregarFormularios();
            
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao salvar formulário: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
    private boolean validarCamposObrigatorios() {
        StringBuilder erros = new StringBuilder();
        
        if (txtNomeCrianca.getText().trim().isEmpty()) {
            erros.append("- Nome da criança é obrigatório\n");
        }
        
        if (dpDataNascimento.getValue() == null) {
            erros.append("- Data de nascimento é obrigatória\n");
        }
        
        if (txtCpfCrianca != null && txtCpfCrianca.getText().trim().isEmpty()) {
            erros.append("- CPF da criança é obrigatório\n");
        }
        
        if (txtNomeResponsavel.getText().trim().isEmpty()) {
            erros.append("- Nome do responsável é obrigatório\n");
        }
        
        if (txtCpfResponsavel.getText().trim().isEmpty()) {
            erros.append("- CPF do responsável é obrigatório\n");
        }
        
        if (txtRgResponsavel.getText().trim().isEmpty()) {
            erros.append("- RG do responsável é obrigatório\n");
        }
        
        if (txtTelefone.getText().trim().isEmpty()) {
            erros.append("- Telefone do responsável é obrigatório\n");
        }
        
        if (txtEndereco.getText().trim().isEmpty()) {
            erros.append("- Endereço é obrigatório\n");
        }
        
        if (txtBairro.getText().trim().isEmpty()) {
            erros.append("- Bairro é obrigatório\n");
        }
        
        if (txtMunicipio.getText().trim().isEmpty()) {
            erros.append("- Município é obrigatório\n");
        }
        
        if (cbStatusFormulario != null && cbStatusFormulario.getValue() == null) {
            erros.append("- Status do formulário é obrigatório\n");
        }
        
        if (erros.length() > 0) {
            mostrarAlerta("Campos Obrigatórios", "Por favor, preencha os seguintes campos:\n\n" + erros.toString(), Alert.AlertType.WARNING);
            return false;
        }
        
        return true;
    }
    
    private void limparCampos() {
        // Limpar dados da criança
        txtNomeCrianca.clear();
        dpDataNascimento.setValue(null);
        txtRgCrianca.clear();
        txtCpfCrianca.clear();
        cbSexo.setValue(null);
        cbRaca.setValue(null);
        
        // Limpar campos adicionais da criança
        if (cbGemeo != null) cbGemeo.setValue(null);
        if (cbPossuiIrmaoNaCreche != null) cbPossuiIrmaoNaCreche.setValue(null);
        if (txtCadastroNacionalSus != null) txtCadastroNacionalSus.clear();
        if (txtUnidadeSaude != null) txtUnidadeSaude.clear();
        if (cbProblemasSaude != null) cbProblemasSaude.setValue(null);
        if (txtMunicipioNascimento != null) txtMunicipioNascimento.clear();
        if (txtCartorioRegistro != null) txtCartorioRegistro.clear();
        if (txtMunicipioRegistro != null) txtMunicipioRegistro.clear();
        if (dpDataEmissaoRg != null) dpDataEmissaoRg.setValue(null);
        if (txtOrgaoEmissorRg != null) txtOrgaoEmissorRg.clear();
        
        // Limpar dados do responsável
        txtNomeResponsavel.clear();
        txtCpfResponsavel.clear();
        txtRgResponsavel.clear();
        txtTelefone.clear();
        
        // Limpar campos adicionais do responsável
        if (cbParentesco != null) cbParentesco.setValue(null);
        if (txtWhatsapp != null) txtWhatsapp.clear();
        if (txtEmail != null) txtEmail.clear();
        if (dpDataNascimentoResponsavel != null) dpDataNascimentoResponsavel.setValue(null);
        if (txtMunicipioNascimentoResponsavel != null) txtMunicipioNascimentoResponsavel.clear();
        if (txtEscolaridade != null) txtEscolaridade.clear();
        if (txtProfissao != null) txtProfissao.clear();
        if (txtRendaFamiliar != null) txtRendaFamiliar.clear();
        
        // Limpar endereço
        txtEndereco.clear();
        txtNumero.clear();
        txtBairro.clear();
        txtMunicipio.clear();
        txtCep.clear();
        txtUf.setText("MA");
        if (txtComplemento != null) txtComplemento.clear();
        
        // Limpar campos de moradia
        if (cbTipoMoradia != null) cbTipoMoradia.setValue(null);
        if (cbTipoPiso != null) cbTipoPiso.setValue(null);
        if (cbSituacaoCasa != null) cbSituacaoCasa.setValue(null);
        if (txtValorAluguel != null) txtValorAluguel.clear();
        if (txtNumeroComodos != null) txtNumeroComodos.clear();
        
        // Limpar móveis do domicílio
        if (chkMovelTV != null) chkMovelTV.setSelected(false);
        if (chkMovelDVD != null) chkMovelDVD.setSelected(false);
        if (chkMovelRadio != null) chkMovelRadio.setSelected(false);
        if (chkMovelComputador != null) chkMovelComputador.setSelected(false);
        if (chkMovelNotebook != null) chkMovelNotebook.setSelected(false);
        if (chkMovelTelefoneFixo != null) chkMovelTelefoneFixo.setSelected(false);
        if (chkMovelTelefoneCelular != null) chkMovelTelefoneCelular.setSelected(false);
        if (chkMovelTablet != null) chkMovelTablet.setSelected(false);
        if (chkMovelInternet != null) chkMovelInternet.setSelected(false);
        if (chkMovelTVAssinatura != null) chkMovelTVAssinatura.setSelected(false);
        if (chkMovelFogao != null) chkMovelFogao.setSelected(false);
        if (chkMovelGeladeira != null) chkMovelGeladeira.setSelected(false);
        if (chkMovelFreezer != null) chkMovelFreezer.setSelected(false);
        if (chkMovelMicroOndas != null) chkMovelMicroOndas.setSelected(false);
        if (chkMovelMaquinaLavar != null) chkMovelMaquinaLavar.setSelected(false);
        if (chkMovelArCondicionado != null) chkMovelArCondicionado.setSelected(false);
        if (chkMovelBicicleta != null) chkMovelBicicleta.setSelected(false);
        if (chkMovelMoto != null) chkMovelMoto.setSelected(false);
        if (chkMovelAutomovel != null) chkMovelAutomovel.setSelected(false);
        
        // Limpar campos de saúde e benefícios
        if (chkRestricaoAlimentarLactose != null) chkRestricaoAlimentarLactose.setSelected(false);
        if (chkRestricaoAlimentarGluten != null) chkRestricaoAlimentarGluten.setSelected(false);
        if (chkRestricaoAlimentarAcucar != null) chkRestricaoAlimentarAcucar.setSelected(false);
        if (chkRestricaoAlimentarSal != null) chkRestricaoAlimentarSal.setSelected(false);
        if (chkRestricaoAlimentarCorante != null) chkRestricaoAlimentarCorante.setSelected(false);
        
        if (chkAlergiaPoeira != null) chkAlergiaPoeira.setSelected(false);
        if (chkAlergiaMedicamentos != null) chkAlergiaMedicamentos.setSelected(false);
        if (chkAlergiaAlimentos != null) chkAlergiaAlimentos.setSelected(false);
        if (chkAlergiaAnimais != null) chkAlergiaAnimais.setSelected(false);
        if (chkAlergiaCosmeticos != null) chkAlergiaCosmeticos.setSelected(false);
        
        if (chkMobilidadeCadeirante != null) chkMobilidadeCadeirante.setSelected(false);
        if (chkMobilidadeMuletas != null) chkMobilidadeMuletas.setSelected(false);
        if (chkMobilidadeProtese != null) chkMobilidadeProtese.setSelected(false);
        if (chkMobilidadeBengala != null) chkMobilidadeBengala.setSelected(false);
        
        // Limpar campos de educação especial
        if (chkAltasHabilidades != null) chkAltasHabilidades.setSelected(false);
        if (chkCegueira != null) chkCegueira.setSelected(false);
        if (chkDeficienciaAuditivaLeve != null) chkDeficienciaAuditivaLeve.setSelected(false);
        if (chkDeficienciaAuditivaSevera != null) chkDeficienciaAuditivaSevera.setSelected(false);
        if (chkDeficienciaAuditivaProcessamento != null) chkDeficienciaAuditivaProcessamento.setSelected(false);
        if (chkDeficienciaVisualBaixa != null) chkDeficienciaVisualBaixa.setSelected(false);
        if (chkDeficienciaFisicaCadeirante != null) chkDeficienciaFisicaCadeirante.setSelected(false);
        if (chkDeficienciaFisicaParalisia != null) chkDeficienciaFisicaParalisia.setSelected(false);
        if (chkDeficienciaFisicaParaplegia != null) chkDeficienciaFisicaParaplegia.setSelected(false);
        if (chkDeficienciaFisicaOutros != null) chkDeficienciaFisicaOutros.setSelected(false);
        if (chkDisfemiaGagueira != null) chkDisfemiaGagueira.setSelected(false);
        if (chkDeficienciaIntelectual != null) chkDeficienciaIntelectual.setSelected(false);
        if (chkSensorialAltaSensibilidade != null) chkSensorialAltaSensibilidade.setSelected(false);
        if (chkSensorialBaixaSensibilidade != null) chkSensorialBaixaSensibilidade.setSelected(false);
        if (chkDeficienciaMental != null) chkDeficienciaMental.setSelected(false);
        if (chkEspectroAutistaNivel1 != null) chkEspectroAutistaNivel1.setSelected(false);
        if (chkEspectroAutistaNivel2 != null) chkEspectroAutistaNivel2.setSelected(false);
        if (chkEspectroAutistaNivel3 != null) chkEspectroAutistaNivel3.setSelected(false);
        if (chkEstrabismo != null) chkEstrabismo.setSelected(false);
        if (chkSurdez != null) chkSurdez.setSelected(false);
        if (chkSindromeDown != null) chkSindromeDown.setSelected(false);
        if (chkTEA != null) chkTEA.setSelected(false);
        if (chkTDAH != null) chkTDAH.setSelected(false);
        if (chkTOD != null) chkTOD.setSelected(false);
        
        // Limpar pessoas autorizadas
        if (txtNomePessoa1 != null) txtNomePessoa1.clear();
        if (cbParentescoPessoa1 != null) cbParentescoPessoa1.setValue(null);
        if (txtNomePessoa2 != null) txtNomePessoa2.clear();
        if (cbParentescoPessoa2 != null) cbParentescoPessoa2.setValue(null);
        if (txtNomePessoa3 != null) txtNomePessoa3.clear();
        if (cbParentescoPessoa3 != null) cbParentescoPessoa3.setValue(null);
        
        // Resetar status para PENDENTE
        if (cbStatusFormulario != null) cbStatusFormulario.setValue(Status.PENDENTE);
    }
    
    private void limparCamposAuxilios() {
        // Método específico para limpar apenas os campos de auxílios
        if (chkAuxilioEmergencial != null) chkAuxilioEmergencial.setSelected(false);
        if (chkAuxilioBrasil != null) chkAuxilioBrasil.setSelected(false);
        if (chkSeguroDesemprego != null) chkSeguroDesemprego.setSelected(false);
        if (chkBeneficioAssistencial != null) chkBeneficioAssistencial.setSelected(false);
        if (chkAuxilioMaternidade != null) chkAuxilioMaternidade.setSelected(false);
        if (chkPensaoMorte != null) chkPensaoMorte.setSelected(false);
        if (chkOutrosAuxilios != null) chkOutrosAuxilios.setSelected(false);
    }
    
    private void limparCamposEducacaoEspecial() {
        // Método específico para limpar apenas os campos de educação especial
        if (chkAltasHabilidades != null) chkAltasHabilidades.setSelected(false);
        if (chkCegueira != null) chkCegueira.setSelected(false);
        if (chkDeficienciaAuditivaLeve != null) chkDeficienciaAuditivaLeve.setSelected(false);
        if (chkDeficienciaAuditivaSevera != null) chkDeficienciaAuditivaSevera.setSelected(false);
        if (chkDeficienciaAuditivaProcessamento != null) chkDeficienciaAuditivaProcessamento.setSelected(false);
        if (chkDeficienciaVisualBaixa != null) chkDeficienciaVisualBaixa.setSelected(false);
        if (chkDeficienciaFisicaCadeirante != null) chkDeficienciaFisicaCadeirante.setSelected(false);
        if (chkDeficienciaFisicaParalisia != null) chkDeficienciaFisicaParalisia.setSelected(false);
        if (chkDeficienciaFisicaParaplegia != null) chkDeficienciaFisicaParaplegia.setSelected(false);
        if (chkDeficienciaFisicaOutros != null) chkDeficienciaFisicaOutros.setSelected(false);
        if (chkDisfemiaGagueira != null) chkDisfemiaGagueira.setSelected(false);
        if (chkDeficienciaIntelectual != null) chkDeficienciaIntelectual.setSelected(false);
        if (chkSensorialAltaSensibilidade != null) chkSensorialAltaSensibilidade.setSelected(false);
        if (chkSensorialBaixaSensibilidade != null) chkSensorialBaixaSensibilidade.setSelected(false);
        if (chkDeficienciaMental != null) chkDeficienciaMental.setSelected(false);
        if (chkEspectroAutistaNivel1 != null) chkEspectroAutistaNivel1.setSelected(false);
        if (chkEspectroAutistaNivel2 != null) chkEspectroAutistaNivel2.setSelected(false);
        if (chkEspectroAutistaNivel3 != null) chkEspectroAutistaNivel3.setSelected(false);
        if (chkEstrabismo != null) chkEstrabismo.setSelected(false);
        if (chkSurdez != null) chkSurdez.setSelected(false);
        if (chkSindromeDown != null) chkSindromeDown.setSelected(false);
        if (chkTEA != null) chkTEA.setSelected(false);
        if (chkTDAH != null) chkTDAH.setSelected(false);
        if (chkTOD != null) chkTOD.setSelected(false);
        
        if (cbRecebeBeneficioSocial != null) cbRecebeBeneficioSocial.setValue(null);
        if (txtNis != null) txtNis.clear();
        
        if (chkAuxilioEmergencial != null) chkAuxilioEmergencial.setSelected(false);
        if (chkAuxilioBrasil != null) chkAuxilioBrasil.setSelected(false);
        if (chkSeguroDesemprego != null) chkSeguroDesemprego.setSelected(false);
        if (chkBeneficioAssistencial != null) chkBeneficioAssistencial.setSelected(false);
        if (chkAuxilioMaternidade != null) chkAuxilioMaternidade.setSelected(false);
        if (chkPensaoMorte != null) chkPensaoMorte.setSelected(false);
        if (chkOutrosAuxilios != null) chkOutrosAuxilios.setSelected(false);
    }
    
    private void buscarPorCpf() {
        String cpf = txtCpfBusca.getText().trim();
        if (cpf.isEmpty()) {
            mostrarAlerta("Aviso", "Digite um CPF para buscar", Alert.AlertType.WARNING);
            return;
        }
        
        try {
            List<FormularioCompleto> formularios = apiService.buscarPorCpf(cpf);
            if (!formularios.isEmpty()) {
                FormularioCompleto formulario = selecionarFormulario(formularios);
                if (formulario != null) {
                    criarAbaVisualizacao(formulario);
                    txtCpfBusca.clear(); // Limpar campo de busca
                    mostrarAlerta("Sucesso", "Formulário encontrado e aberto para visualização!", Alert.AlertType.INFORMATION);
                }
            } else {
                mostrarAlerta("Não encontrado", "Nenhum formulário encontrado para o CPF informado", Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao buscar formulário: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void editarPorCpf() {
        String cpf = txtCpfBusca.getText().trim();
        if (cpf.isEmpty()) {
            mostrarAlerta("Aviso", "Digite um CPF para editar", Alert.AlertType.WARNING);
            return;
        }
        
        try {
            List<FormularioCompleto> formularios = apiService.buscarPorCpf(cpf);
            if (!formularios.isEmpty()) {
                FormularioCompleto formulario = selecionarFormulario(formularios);
                if (formulario != null) {
                    criarAbaEdicao(formulario);
                    txtCpfBusca.clear(); // Limpar campo de busca
                    String statusAtual = formulario.getStatus() != null ? formulario.getStatus().toString() : "NÃO DEFINIDO";
                    mostrarAlerta("Sucesso", 
                        "Formulário encontrado e aberto para edição!\n" +
                        "Status atual: " + statusAtual + "\n" +
                        "Você pode alterar o status na seção 'Dados Escolares'", 
                        Alert.AlertType.INFORMATION);
                }
            } else {
                mostrarAlerta("Não encontrado", "Nenhum formulário encontrado para o CPF informado", Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao buscar formulário: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void buscarPorStatus() {
        Status statusSelecionado = cbStatusBusca.getValue();
        if (statusSelecionado == null) {
            mostrarAlerta("Aviso", "Selecione um status para buscar", Alert.AlertType.WARNING);
            return;
        }
        
        try {
            List<FormularioCompleto> formularios = apiService.buscarPorStatus(statusSelecionado.toString());
            
            if (formularios.isEmpty()) {
                mostrarAlerta("Não encontrado", 
                    "Nenhum formulário encontrado com status: " + statusSelecionado, 
                    Alert.AlertType.INFORMATION);
                return;
            }
            
            // Criar uma aba com lista dos formulários encontrados
            criarAbaListagemStatus(formularios, statusSelecionado);
            cbStatusBusca.setValue(null); // Limpar seleção
            
            mostrarAlerta("Sucesso", 
                "Encontrados " + formularios.size() + " formulário(s) com status: " + statusSelecionado, 
                Alert.AlertType.INFORMATION);
                
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao buscar formulários por status: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void criarAbaVisualizacao(FormularioCompleto formulario) {
        try {
            // Criar uma nova aba para visualização
            Tab novaAba = new Tab();
            novaAba.setText("👁️ " + formulario.getNomeCrianca() + " (ID: " + formulario.getId() + ")");
            novaAba.setClosable(true);
            
            // Criar o conteúdo da aba (somente visualização)
            VBox conteudoAba = criarConteudoAbaVisualizacao(formulario);
            novaAba.setContent(conteudoAba);
            
            // Adicionar a aba ao TabPane
            tabPane.getTabs().add(novaAba);
            
            // Selecionar a nova aba
            tabPane.getSelectionModel().select(novaAba);
            
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao criar aba de visualização: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
    private void criarAbaEdicao(FormularioCompleto formulario) {
        try {
            // Criar uma nova aba
            Tab novaAba = new Tab();
            novaAba.setText("✏️ " + formulario.getNomeCrianca() + " (ID: " + formulario.getId() + ")");
            novaAba.setClosable(true);
            
            // Criar o conteúdo da aba (cópia dos campos principais)
            VBox conteudoAba = criarConteudoAbaEdicao(formulario);
            novaAba.setContent(conteudoAba);
            
            // Adicionar a aba ao TabPane
            tabPane.getTabs().add(novaAba);
            
            // Selecionar a nova aba
            tabPane.getSelectionModel().select(novaAba);
            
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao criar aba de edição: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
    private void criarAbaListagemStatus(List<FormularioCompleto> formularios, Status status) {
        try {
            // Criar uma nova aba para listagem
            Tab novaAba = new Tab();
            novaAba.setText("📋 Status: " + status + " (" + formularios.size() + ")");
            novaAba.setClosable(true);
            
            // Criar o conteúdo da aba com lista de formulários
            VBox conteudoAba = new VBox(10);
            conteudoAba.setPadding(new Insets(20));
            
            Label titulo = new Label("📋 Formulários com Status: " + status);
            titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
            
            ScrollPane scrollPane = new ScrollPane();
            VBox listaFormularios = new VBox(15);
            
            for (FormularioCompleto formulario : formularios) {
                HBox itemFormulario = criarItemFormulario(formulario);
                listaFormularios.getChildren().add(itemFormulario);
            }
            
            scrollPane.setContent(listaFormularios);
            scrollPane.setFitToWidth(true);
            scrollPane.setPrefHeight(400);
            
            conteudoAba.getChildren().addAll(titulo, scrollPane);
            novaAba.setContent(conteudoAba);
            
            // Adicionar a aba ao TabPane
            tabPane.getTabs().add(novaAba);
            
            // Selecionar a nova aba
            tabPane.getSelectionModel().select(novaAba);
            
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao criar aba de listagem: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private HBox criarItemFormulario(FormularioCompleto formulario) {
        HBox item = new HBox(15);
        item.setAlignment(Pos.CENTER_LEFT);
        item.setPadding(new Insets(10));
        item.setStyle("-fx-border-color: #ddd; -fx-border-width: 1px; -fx-background-color: #f8f9fa; -fx-border-radius: 5px;");
        
        // Informações do formulário
        VBox infoBox = new VBox(5);
        Label nomeLabel = new Label("👶 " + formulario.getNomeCrianca());
        nomeLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        
        String responsavelNome = "Sem responsável";
        if (formulario.getResponsavel() != null && !formulario.getResponsavel().isEmpty()) {
            responsavelNome = formulario.getResponsavel().iterator().next().getNome();
        }
        Label responsavelLabel = new Label("👤 Responsável: " + responsavelNome);
        
        Label statusLabel = new Label("⭐ Status: " + formulario.getStatus());
        statusLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #ff6600;");
        
        Label idLabel = new Label("🆔 ID: " + formulario.getId());
        idLabel.setStyle("-fx-text-fill: #666;");
        
        infoBox.getChildren().addAll(nomeLabel, responsavelLabel, statusLabel, idLabel);
        
        // Botões de ação
        VBox botoesBox = new VBox(5);
        Button btnVisualizar = new Button("👁️ Visualizar");
        btnVisualizar.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        btnVisualizar.setOnAction(e -> criarAbaVisualizacao(formulario));
        
        Button btnEditarStatus = new Button("✏️ Editar");
        btnEditarStatus.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        btnEditarStatus.setOnAction(e -> criarAbaEdicao(formulario));
        
        botoesBox.getChildren().addAll(btnVisualizar, btnEditarStatus);
        
        item.getChildren().addAll(infoBox, new Region(), botoesBox);
        HBox.setHgrow(infoBox, Priority.ALWAYS);
        
        return item;
    }
    
    private VBox criarConteudoAbaVisualizacao(FormularioCompleto formulario) {
        VBox container = new VBox();
        container.setSpacing(15);
        container.setPadding(new javafx.geometry.Insets(20));
        
        // Título da aba
        Label titulo = new Label("📋 Visualizando Formulário - " + formulario.getNomeCrianca());
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #1976d2;");
        container.getChildren().add(titulo);
        
        Label subtitulo = new Label("💡 Modo somente visualização - Os dados não podem ser editados");
        subtitulo.setStyle("-fx-font-size: 12px; -fx-text-fill: #666; -fx-padding: 0 0 10 0;");
        container.getChildren().add(subtitulo);
        
        // Criar campos somente leitura (usar os mesmos métodos, mas desabilitar interação depois)
        VBox dadosCrianca = criarSecaoDadosCrianca(formulario);
        VBox dadosResponsavel = criarSecaoDadosResponsavel(formulario);
        VBox dadosSaude = criarSecaoDadosSaude(formulario);
        VBox dadosSociais = criarSecaoDadosSociais(formulario);
        VBox dadosHabitacao = criarSecaoDadosHabitacao(formulario);
        VBox dadosEscola = criarSecaoDadosEscola(formulario);
        VBox dadosFamilia = criarSecaoDadosFamilia(formulario);
        
        // Desabilitar todos os campos para visualização apenas
        desabilitarEdicaoRecursiva(dadosCrianca);
        desabilitarEdicaoRecursiva(dadosResponsavel);
        desabilitarEdicaoRecursiva(dadosSaude);
        desabilitarEdicaoRecursiva(dadosSociais);
        desabilitarEdicaoRecursiva(dadosHabitacao);
        desabilitarEdicaoRecursiva(dadosEscola);
        
        // Botão para fechar
        HBox botoes = new HBox();
        botoes.setSpacing(10);
        botoes.setAlignment(javafx.geometry.Pos.CENTER);
        
        Button btnFechar = new Button("❌ Fechar");
        btnFechar.setStyle("-fx-background-color: #607d8b; -fx-text-fill: white; -fx-font-weight: bold;");
        btnFechar.setOnAction(e -> {
            // Fechar a aba atual
            tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
        });
        
        botoes.getChildren().add(btnFechar);
        
        container.getChildren().addAll(dadosCrianca, dadosResponsavel, dadosSaude, dadosSociais, dadosHabitacao, dadosEscola, dadosFamilia, botoes);
        
        // Colocar em um ScrollPane
        ScrollPane scroll = new ScrollPane(container);
        scroll.setFitToWidth(true);
        
        VBox wrapper = new VBox(scroll);
        VBox.setVgrow(scroll, javafx.scene.layout.Priority.ALWAYS);
        
        return wrapper;
    }
    
    private VBox criarConteudoAbaEdicao(FormularioCompleto formulario) {
        VBox container = new VBox();
        container.setSpacing(15);
        container.setPadding(new javafx.geometry.Insets(20));
        
        // Título da aba
        Label titulo = new Label("✏️ Editando Formulário - " + formulario.getNomeCrianca());
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #4CAF50;");
        container.getChildren().add(titulo);
        
        Label subtitulo = new Label("⚠️ Modo edição ativo - Faça as alterações necessárias e salve");
        subtitulo.setStyle("-fx-font-size: 12px; -fx-text-fill: #f57c00; -fx-padding: 0 0 10 0;");
        container.getChildren().add(subtitulo);
        
        // Criar campos editáveis
        VBox dadosCrianca = criarSecaoDadosCrianca(formulario);
        VBox dadosResponsavel = criarSecaoDadosResponsavel(formulario);
        VBox dadosSaude = criarSecaoDadosSaude(formulario);
        VBox dadosSociais = criarSecaoDadosSociais(formulario);
        VBox dadosHabitacao = criarSecaoDadosHabitacao(formulario);
        VBox dadosEscola = criarSecaoDadosEscola(formulario);
        VBox dadosFamilia = criarSecaoDadosFamilia(formulario);
        
        // Botões de ação
        HBox botoes = new HBox();
        botoes.setSpacing(10);
        botoes.setAlignment(javafx.geometry.Pos.CENTER);
        
        Button btnSalvarEdicao = new Button("💾 Salvar Alterações");
        btnSalvarEdicao.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnSalvarEdicao.setOnAction(e -> salvarEdicao(formulario));
        
        Button btnCancelar = new Button("❌ Cancelar");
        btnCancelar.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
        btnCancelar.setOnAction(e -> {
            // Fechar a aba atual
            tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
        });
        
        botoes.getChildren().addAll(btnSalvarEdicao, btnCancelar);
        
        container.getChildren().addAll(dadosCrianca, dadosResponsavel, dadosSaude, dadosSociais, dadosHabitacao, dadosEscola, dadosFamilia, botoes);
        
        // Colocar em um ScrollPane
        ScrollPane scroll = new ScrollPane(container);
        scroll.setFitToWidth(true);
        
        VBox wrapper = new VBox(scroll);
        VBox.setVgrow(scroll, javafx.scene.layout.Priority.ALWAYS);
        
        return wrapper;
    }
    
    private VBox criarSecaoDadosCrianca(FormularioCompleto formulario) {
        VBox secao = new VBox();
        secao.setSpacing(8);
        secao.setPadding(new Insets(15));
        secao.setStyle("-fx-border-color: #ddd; -fx-border-width: 1px; -fx-background-color: #f9f9f9;");
        
        Label titulo = new Label("� Dados da Criança");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        secao.getChildren().add(titulo);
        
        // Inicializar campos se necessário
        if (camposAtual == null) {
            camposAtual = new CamposEdicao();
        }
        
        // Campos básicos da criança
        camposAtual.txtNome = new TextField(formulario.getNomeCrianca());
        camposAtual.txtNome.setPromptText("Nome da criança");
        
        camposAtual.dpDataNasc = new DatePicker(formulario.getDataNascimento());
        
        camposAtual.txtRg = new TextField(formulario.getRg() != null ? formulario.getRg() : "");
        camposAtual.txtRg.setPromptText("RG da criança");
        
        camposAtual.txtCpf = new TextField(formulario.getCpf() != null ? formulario.getCpf() : "");
        camposAtual.txtCpf.setPromptText("CPF da criança");
        
        camposAtual.dpEmissaoRg = new DatePicker(formulario.getDataEmissaoRg());
        
        camposAtual.txtOrgaoEmissor = new TextField(formulario.getOrgaoEmissorRg() != null ? formulario.getOrgaoEmissorRg() : "");
        camposAtual.txtOrgaoEmissor.setPromptText("Órgão emissor RG");
        
        camposAtual.cbSexo = new ComboBox<>(FXCollections.observableArrayList(Sexo.values()));
        camposAtual.cbSexo.setValue(formulario.getSexo());
        
        camposAtual.cbRaca = new ComboBox<>(FXCollections.observableArrayList(Raca.values()));
        camposAtual.cbRaca.setValue(formulario.getRaca());
        
        camposAtual.cbGemeo = new ComboBox<>(FXCollections.observableArrayList(SimNao.values()));
        camposAtual.cbGemeo.setValue(formulario.getGemeo());
        
        camposAtual.cbIrmaoCreche = new ComboBox<>(FXCollections.observableArrayList(SimNao.values()));
        camposAtual.cbIrmaoCreche.setValue(formulario.getPossuiIrmaoNaCreche());
        
        camposAtual.txtMunicipioNasc = new TextField(formulario.getMunicipioNascimento() != null ? formulario.getMunicipioNascimento() : "");
        camposAtual.txtMunicipioNasc.setPromptText("Município de nascimento");
        
        camposAtual.txtCartorio = new TextField(formulario.getCartorioRegistro() != null ? formulario.getCartorioRegistro() : "");
        camposAtual.txtCartorio.setPromptText("Cartório de registro");
        
        camposAtual.txtMunicipioReg = new TextField(formulario.getMunicipioRegistro() != null ? formulario.getMunicipioRegistro() : "");
        camposAtual.txtMunicipioReg.setPromptText("Município de registro");
        
        secao.getChildren().addAll(
            new Label("Nome da Criança:"), camposAtual.txtNome,
            new Label("Data de Nascimento:"), camposAtual.dpDataNasc,
            new Label("RG:"), camposAtual.txtRg,
            new Label("Data Emissão RG:"), camposAtual.dpEmissaoRg,
            new Label("Órgão Emissor:"), camposAtual.txtOrgaoEmissor,
            new Label("CPF:"), camposAtual.txtCpf,
            new Label("Sexo:"), camposAtual.cbSexo,
            new Label("Raça:"), camposAtual.cbRaca,
            new Label("É gêmeo:"), camposAtual.cbGemeo,
            new Label("Possui irmão na creche:"), camposAtual.cbIrmaoCreche,
            new Label("Município de Nascimento:"), camposAtual.txtMunicipioNasc,
            new Label("Cartório de Registro:"), camposAtual.txtCartorio,
            new Label("Município de Registro:"), camposAtual.txtMunicipioReg
        );
        
        return secao;
    }
    
    private VBox criarSecaoDadosResponsavel(FormularioCompleto formulario) {
        VBox secao = new VBox();
        secao.setSpacing(8);
        secao.setPadding(new Insets(15));
        secao.setStyle("-fx-border-color: #ddd; -fx-border-width: 1px; -fx-background-color: #f9f9f9;");
        
        Label titulo = new Label("👤 Dados do Responsável");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        secao.getChildren().add(titulo);
        
        if (formulario.getResponsavel() != null && !formulario.getResponsavel().isEmpty()) {
            // Pegar o primeiro responsável
            Responsavel resp = formulario.getResponsavel().iterator().next();
            
            camposAtual.txtNomeResp = new TextField(resp.getNome() != null ? resp.getNome() : "");
            camposAtual.txtNomeResp.setPromptText("Nome do responsável");
            
            camposAtual.txtCpfResp = new TextField(resp.getCpf() != null ? resp.getCpf() : "");
            camposAtual.txtCpfResp.setPromptText("CPF do responsável");
            
            camposAtual.txtRgResp = new TextField(resp.getRg() != null ? resp.getRg() : "");
            camposAtual.txtRgResp.setPromptText("RG do responsável");
            
            camposAtual.txtTelefone = new TextField(resp.getTelefone() != null ? resp.getTelefone() : "");
            camposAtual.txtTelefone.setPromptText("Telefone");
            
            secao.getChildren().addAll(
                new Label("Nome do Responsável:"), camposAtual.txtNomeResp,
                new Label("CPF do Responsável:"), camposAtual.txtCpfResp,
                new Label("RG do Responsável:"), camposAtual.txtRgResp,
                new Label("Telefone:"), camposAtual.txtTelefone
            );
            
            // Dados do endereço
            if (resp.getEndereco() != null) {
                Endereco end = resp.getEndereco();
                
                Label tituloEnd = new Label("🏠 Endereço");
                tituloEnd.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #555; -fx-padding: 10 0 5 0;");
                
                camposAtual.txtEndereco = new TextField(end.getEndereco() != null ? end.getEndereco() : "");
                camposAtual.txtEndereco.setPromptText("Endereço");
                
                camposAtual.txtNumero = new TextField(end.getNumero() != null ? end.getNumero() : "");
                camposAtual.txtNumero.setPromptText("Número");
                
                camposAtual.txtBairro = new TextField(end.getBairro() != null ? end.getBairro() : "");
                camposAtual.txtBairro.setPromptText("Bairro");
                
                camposAtual.txtMunicipio = new TextField(end.getMunicipio() != null ? end.getMunicipio() : "");
                camposAtual.txtMunicipio.setPromptText("Município");
                
                camposAtual.txtCep = new TextField(end.getCep() != null ? end.getCep() : "");
                camposAtual.txtCep.setPromptText("CEP");
                
                camposAtual.txtUf = new TextField(end.getUF() != null ? end.getUF() : "");
                camposAtual.txtUf.setPromptText("UF");
                
                camposAtual.txtPontoRef = new TextField(end.getPontoReferencia() != null ? end.getPontoReferencia() : "");
                camposAtual.txtPontoRef.setPromptText("Ponto de referência");
                
                camposAtual.txtTelRes = new TextField(end.getTelefoneResidencial() != null ? end.getTelefoneResidencial() : "");
                camposAtual.txtTelRes.setPromptText("Telefone residencial");
                
                secao.getChildren().addAll(
                    tituloEnd,
                    new Label("Endereço:"), camposAtual.txtEndereco,
                    new Label("Número:"), camposAtual.txtNumero,
                    new Label("Bairro:"), camposAtual.txtBairro,
                    new Label("Município:"), camposAtual.txtMunicipio,
                    new Label("CEP:"), camposAtual.txtCep,
                    new Label("UF:"), camposAtual.txtUf,
                    new Label("Ponto de Referência:"), camposAtual.txtPontoRef,
                    new Label("Telefone Residencial:"), camposAtual.txtTelRes
                );
            }
        }
        
        return secao;
    }
    
    private VBox criarSecaoDadosSaude(FormularioCompleto formulario) {
        VBox secao = new VBox();
        secao.setSpacing(8);
        secao.setPadding(new Insets(15));
        secao.setStyle("-fx-border-color: #ddd; -fx-border-width: 1px; -fx-background-color: #f0f8ff;");
        
        Label titulo = new Label("🏥 Dados de Saúde");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        secao.getChildren().add(titulo);
        
        camposAtual.txtSus = new TextField(formulario.getCadastroNacionalSus() != null ? formulario.getCadastroNacionalSus() : "");
        camposAtual.txtSus.setPromptText("Cadastro Nacional SUS");
        
        camposAtual.txtUnidadeSaude = new TextField(formulario.getUnidadeSaude() != null ? formulario.getUnidadeSaude() : "");
        camposAtual.txtUnidadeSaude.setPromptText("Unidade de saúde");
        
        camposAtual.cbProblemasSaude = new ComboBox<>(FXCollections.observableArrayList(SimNao.values()));
        camposAtual.cbProblemasSaude.setValue(formulario.getProblemasSaude());
        
        camposAtual.cbMobilidade = new ComboBox<>(FXCollections.observableArrayList(Mobilidade.values()));
        camposAtual.cbMobilidade.setValue(formulario.getMobilidade());
        
        // Restrições Alimentares
        Label lblRestricoes = new Label("🍽️ Restrições Alimentares:");
        lblRestricoes.setStyle("-fx-font-weight: bold; -fx-text-fill: #d32f2f;");
        
        VBox restricoesBox = new VBox(5);
        for (RestricaoAlimentar restricao : RestricaoAlimentar.values()) {
            CheckBox cb = new CheckBox(restricao.toString());
            if (formulario.getRestricaoAlimentar() != null) {
                cb.setSelected(formulario.getRestricaoAlimentar().contains(restricao));
            }
            camposAtual.restricoesAlimentares.add(cb);
            restricoesBox.getChildren().add(cb);
        }
        
        // Alergias
        Label lblAlergias = new Label("🤧 Alergias:");
        lblAlergias.setStyle("-fx-font-weight: bold; -fx-text-fill: #d32f2f;");
        
        VBox alergiasBox = new VBox(5);
        for (Alergias alergia : Alergias.values()) {
            CheckBox cb = new CheckBox(alergia.toString());
            if (formulario.getAlergia() != null) {
                cb.setSelected(formulario.getAlergia().contains(alergia));
            }
            camposAtual.alergias.add(cb);
            alergiasBox.getChildren().add(cb);
        }
        
        // Educação Especial
        Label lblEducacao = new Label("♿ Educação Especial:");
        lblEducacao.setStyle("-fx-font-weight: bold; -fx-text-fill: #1976d2;");
        
        VBox educacaoBox = new VBox(5);
        for (EducacaoEspecial educacao : EducacaoEspecial.values()) {
            CheckBox cb = new CheckBox(educacao.toString());
            if (formulario.getEducacaoEspecial() != null) {
                cb.setSelected(formulario.getEducacaoEspecial().contains(educacao));
            }
            camposAtual.educacaoEspecial.add(cb);
            educacaoBox.getChildren().add(cb);
        }
        
        secao.getChildren().addAll(
            new Label("Cadastro Nacional SUS:"), camposAtual.txtSus,
            new Label("Unidade de Saúde:"), camposAtual.txtUnidadeSaude,
            new Label("Possui problemas de saúde:"), camposAtual.cbProblemasSaude,
            new Label("Mobilidade:"), camposAtual.cbMobilidade,
            lblRestricoes, restricoesBox,
            lblAlergias, alergiasBox,
            lblEducacao, educacaoBox
        );
        
        return secao;
    }
    
    private VBox criarSecaoDadosSociais(FormularioCompleto formulario) {
        VBox secao = new VBox();
        secao.setSpacing(8);
        secao.setPadding(new Insets(15));
        secao.setStyle("-fx-border-color: #ddd; -fx-border-width: 1px; -fx-background-color: #f0fff0;");
        
        Label titulo = new Label("💰 Dados Socioeconômicos");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        secao.getChildren().add(titulo);
        
        camposAtual.cbBeneficio = new ComboBox<>(FXCollections.observableArrayList(SimNao.values()));
        camposAtual.cbBeneficio.setValue(formulario.getRecebeBeneficioSocial());
        
        camposAtual.txtNis = new TextField(formulario.getNis() != null ? formulario.getNis() : "");
        camposAtual.txtNis.setPromptText("NIS");
        
        camposAtual.txtRendaFamiliar = new TextField(formulario.getRendaFamiliarMensal() != null ? formulario.getRendaFamiliarMensal().toString() : "");
        camposAtual.txtRendaFamiliar.setPromptText("Renda familiar mensal");
        
        camposAtual.txtRendaPerCapita = new TextField(formulario.getRendaPerCapita() != null ? formulario.getRendaPerCapita().toString() : "");
        camposAtual.txtRendaPerCapita.setPromptText("Renda per capita");
        
        // Auxílios Recebidos
        Label lblAuxilios = new Label("💰 Auxílios Recebidos:");
        lblAuxilios.setStyle("-fx-font-weight: bold; -fx-text-fill: #388e3c;");
        
        VBox auxiliosBox = new VBox(5);
        for (Auxilio auxilio : Auxilio.values()) {
            CheckBox cb = new CheckBox(auxilio.toString());
            if (formulario.getAuxilios() != null) {
                cb.setSelected(formulario.getAuxilios().contains(auxilio));
            }
            camposAtual.auxilios.add(cb);
            auxiliosBox.getChildren().add(cb);
        }
        
        secao.getChildren().addAll(
            new Label("Recebe benefício social:"), camposAtual.cbBeneficio,
            new Label("NIS:"), camposAtual.txtNis,
            new Label("Renda Familiar Mensal:"), camposAtual.txtRendaFamiliar,
            new Label("Renda Per Capita:"), camposAtual.txtRendaPerCapita,
            lblAuxilios, auxiliosBox
        );
        
        return secao;
    }
    
    private VBox criarSecaoDadosHabitacao(FormularioCompleto formulario) {
        VBox secao = new VBox();
        secao.setSpacing(8);
        secao.setPadding(new Insets(15));
        secao.setStyle("-fx-border-color: #ddd; -fx-border-width: 1px; -fx-background-color: #fff8dc;");
        
        Label titulo = new Label("🏠 Dados de Habitação");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        secao.getChildren().add(titulo);
        
        camposAtual.txtValorAluguel = new TextField(formulario.getValorAluguel() != null ? formulario.getValorAluguel().toString() : "");
        camposAtual.txtValorAluguel.setPromptText("Valor do aluguel");
        
        camposAtual.txtNumComodos = new TextField(String.valueOf(formulario.getNumeroComodos()));
        camposAtual.txtNumComodos.setPromptText("Número de cômodos");
        
        camposAtual.cbSituacaoCasa = new ComboBox<>(FXCollections.observableArrayList(SituacaoCasa.values()));
        camposAtual.cbSituacaoCasa.setValue(formulario.getSituacaoCasa());
        
        camposAtual.cbFossa = new ComboBox<>(FXCollections.observableArrayList(SimNao.values()));
        camposAtual.cbFossa.setValue(formulario.getTemFossa());
        
        camposAtual.cbCifon = new ComboBox<>(FXCollections.observableArrayList(SimNao.values()));
        camposAtual.cbCifon.setValue(formulario.getTemCifon());
        
        camposAtual.cbEnergia = new ComboBox<>(FXCollections.observableArrayList(SimNao.values()));
        camposAtual.cbEnergia.setValue(formulario.getTemEnergiaEletrica());
        
        camposAtual.cbAgua = new ComboBox<>(FXCollections.observableArrayList(SimNao.values()));
        camposAtual.cbAgua.setValue(formulario.getTemAguaEncanada());
        
        // Inicializar listas de checkboxes
        camposAtual.tiposPiso = new ArrayList<>();
        camposAtual.tiposMoradia = new ArrayList<>();
        camposAtual.moveis = new ArrayList<>();
        
        // Tipos de Piso
        Label lblPiso = new Label("🏠 Tipos de Piso:");
        lblPiso.setStyle("-fx-font-weight: bold; -fx-text-fill: #795548;");
        
        VBox pisoBox = new VBox(5);
        for (TipoPiso piso : TipoPiso.values()) {
            CheckBox cb = new CheckBox(piso.toString());
            if (formulario.getTipoPiso() != null) {
                cb.setSelected(formulario.getTipoPiso().contains(piso));
            }
            camposAtual.tiposPiso.add(cb);
            pisoBox.getChildren().add(cb);
        }
        
        // Tipos de Moradia
        Label lblMoradia = new Label("🏡 Tipos de Moradia:");
        lblMoradia.setStyle("-fx-font-weight: bold; -fx-text-fill: #795548;");
        
        VBox moradiaBox = new VBox(5);
        for (TipoMoradia moradia : TipoMoradia.values()) {
            CheckBox cb = new CheckBox(moradia.toString());
            if (formulario.getTipoMoradia() != null) {
                cb.setSelected(formulario.getTipoMoradia().contains(moradia));
            }
            camposAtual.tiposMoradia.add(cb);
            moradiaBox.getChildren().add(cb);
        }
        
        // Móveis no Domicílio
        Label lblMoveis = new Label("🪑 Móveis no Domicílio:");
        lblMoveis.setStyle("-fx-font-weight: bold; -fx-text-fill: #795548;");
        
        VBox moveisBox = new VBox(5);
        for (Moveis movel : Moveis.values()) {
            CheckBox cb = new CheckBox(movel.toString());
            if (formulario.getNoSeuDomicilioTem() != null) {
                cb.setSelected(formulario.getNoSeuDomicilioTem().contains(movel));
            }
            camposAtual.moveis.add(cb);
            moveisBox.getChildren().add(cb);
        }
        
        secao.getChildren().addAll(
            new Label("Valor do Aluguel:"), camposAtual.txtValorAluguel,
            new Label("Número de Cômodos:"), camposAtual.txtNumComodos,
            new Label("Situação da Casa:"), camposAtual.cbSituacaoCasa,
            new Label("Tem Fossa:"), camposAtual.cbFossa,
            new Label("Tem Cifão:"), camposAtual.cbCifon,
            new Label("Tem Energia Elétrica:"), camposAtual.cbEnergia,
            new Label("Tem Água Encanada:"), camposAtual.cbAgua,
            lblPiso, pisoBox,
            lblMoradia, moradiaBox,
            lblMoveis, moveisBox
        );
        
        return secao;
    }
    
    private VBox criarSecaoDadosEscola(FormularioCompleto formulario) {
        VBox secao = new VBox();
        secao.setSpacing(8);
        secao.setPadding(new Insets(15));
        secao.setStyle("-fx-border-color: #ddd; -fx-border-width: 1px; -fx-background-color: #f5f5f5;");
        
        Label titulo = new Label("🎓 Dados Escolares");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        secao.getChildren().add(titulo);
        
        camposAtual.txtSerie = new TextField(String.valueOf(formulario.getSerieQueIraCursar()));
        camposAtual.txtSerie.setPromptText("Série que irá cursar");
        
        camposAtual.txtAnoLetivo = new TextField(String.valueOf(formulario.getAnoLetivo()));
        camposAtual.txtAnoLetivo.setPromptText("Ano letivo");
        
        camposAtual.dpMatricula = new DatePicker(formulario.getDataMatricula());
        
        camposAtual.dpDesligamento = new DatePicker(formulario.getDataDesligamento());
        
        camposAtual.cbStatus = new ComboBox<>(FXCollections.observableArrayList(Status.values()));
        camposAtual.cbStatus.setValue(formulario.getStatus());
        
        // Destacar o campo de Status
        Label lblStatus = new Label("⭐ Status do Formulário:");
        lblStatus.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #ff6600; -fx-padding: 10 0 5 0;");
        
        camposAtual.cbStatus.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        
        secao.getChildren().addAll(
            new Label("Série que irá cursar:"), camposAtual.txtSerie,
            new Label("Ano Letivo:"), camposAtual.txtAnoLetivo,
            new Label("Data de Matrícula:"), camposAtual.dpMatricula,
            new Label("Data de Desligamento:"), camposAtual.dpDesligamento,
            lblStatus, camposAtual.cbStatus
        );
        
        return secao;
    }
    
    private void salvarEdicao(FormularioCompleto formulario) {
        try {
            // Exibir mensagem de confirmação
            Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacao.setTitle("Confirmar Edição"); 
            confirmacao.setHeaderText("Salvar alterações no formulário?");
            confirmacao.setContentText("Formulário de " + formulario.getNomeCrianca() + " (ID: " + formulario.getId() + ")");
            
            Optional<ButtonType> resultado = confirmacao.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                
                // Coletar dados editados da aba atual
                FormularioCompleto formularioEditado = coletarDadosEdicao(formulario);
                
                String resposta = apiService.editarFormulario(formularioEditado.getId(), formularioEditado);
                
                if (resposta != null && resposta.contains("sucesso")) {
                    mostrarAlerta("Sucesso", "Formulário editado com sucesso!", Alert.AlertType.INFORMATION);
                    
                    // Fechar a aba de edição
                    tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedItem());
                } else {
                    mostrarAlerta("Erro", "Erro na resposta do servidor: " + resposta, Alert.AlertType.ERROR);
                }
            }
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao salvar edição: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
    private VBox criarSecaoDadosFamilia(FormularioCompleto formulario) {
        VBox secao = new VBox();
        secao.setSpacing(8);
        secao.setPadding(new Insets(15));
        secao.setStyle("-fx-border-color: #ddd; -fx-border-width: 1px; -fx-background-color: #fce4ec;");
        
        Label titulo = new Label("👨‍👩‍👧‍👦 Composição Familiar e Pessoas Autorizadas");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        secao.getChildren().add(titulo);
        
        // Composição Familiar
        if (formulario.getComposicaoFamiliar() != null && !formulario.getComposicaoFamiliar().isEmpty()) {
            Label lblComposicao = new Label("👪 Composição Familiar:");
            lblComposicao.setStyle("-fx-font-weight: bold; -fx-text-fill: #1976d2;");
            secao.getChildren().add(lblComposicao);
            
            for (ComposicaoFamiliar cf : formulario.getComposicaoFamiliar()) {
                Label lblMembro = new Label("• " + cf.getNomeCompleto() + 
                    " - " + cf.getGrauParentesco() + 
                    " - Idade: " + cf.getIdade() + 
                    " - " + cf.getSituacaoEmprego());
                lblMembro.setStyle("-fx-padding: 5 0 0 20;");
                secao.getChildren().add(lblMembro);
            }
        }
        
        // Pessoas Autorizadas
        if (formulario.getPessoasAutorizadas() != null && !formulario.getPessoasAutorizadas().isEmpty()) {
            Label lblAutorizadas = new Label("✅ Pessoas Autorizadas:");
            lblAutorizadas.setStyle("-fx-font-weight: bold; -fx-text-fill: #388e3c; -fx-padding: 10 0 5 0;");
            secao.getChildren().add(lblAutorizadas);
            
            for (PessoasAutorizadas pa : formulario.getPessoasAutorizadas()) {
                Label lblPessoa = new Label("• " + pa.getNomeCompleto() + 
                    " - " + pa.getParentesco() + 
                    " - RG: " + (pa.getRg() != null ? pa.getRg() : "N/A") +
                    " - Tel: " + (pa.getTelefoneContato() != null ? pa.getTelefoneContato() : "N/A"));
                lblPessoa.setStyle("-fx-padding: 5 0 0 20;");
                secao.getChildren().add(lblPessoa);
            }
        }
        
        return secao;
    }
    
    // Classe interna para armazenar referências dos campos de edição
    private static class CamposEdicao {
        // Dados da criança
        TextField txtNome, txtRg, txtCpf, txtOrgaoEmissor, txtMunicipioNasc, txtCartorio, txtMunicipioReg;
        DatePicker dpDataNasc, dpEmissaoRg;
        ComboBox<Sexo> cbSexo;
        ComboBox<Raca> cbRaca;
        ComboBox<SimNao> cbGemeo, cbIrmaoCreche;
        
        // Dados do responsável
        TextField txtNomeResp, txtCpfResp, txtRgResp, txtTelefone;
        TextField txtEndereco, txtNumero, txtBairro, txtMunicipio, txtCep, txtUf, txtPontoRef, txtTelRes;
        
        // Dados de saúde
        TextField txtSus, txtUnidadeSaude;
        ComboBox<SimNao> cbProblemasSaude;
        ComboBox<Mobilidade> cbMobilidade;
        List<CheckBox> restricoesAlimentares = new ArrayList<>();
        List<CheckBox> alergias = new ArrayList<>();
        List<CheckBox> educacaoEspecial = new ArrayList<>();
        
        // Dados sociais
        ComboBox<SimNao> cbBeneficio;
        TextField txtNis, txtRendaFamiliar, txtRendaPerCapita;
        List<CheckBox> auxilios = new ArrayList<>();
        
        // Dados habitação
        TextField txtValorAluguel, txtNumComodos;
        ComboBox<SituacaoCasa> cbSituacaoCasa;
        ComboBox<SimNao> cbFossa, cbCifon, cbEnergia, cbAgua;
        List<CheckBox> tiposPiso = new ArrayList<>();
        List<CheckBox> tiposMoradia = new ArrayList<>();
        List<CheckBox> moveis = new ArrayList<>();
        
        // Dados escola
        TextField txtSerie, txtAnoLetivo;
        DatePicker dpMatricula, dpDesligamento;
        ComboBox<Status> cbStatus;
    }
    
    // Armazenar referência dos campos da aba em edição atual
    private CamposEdicao camposAtual = null;
    
    private FormularioCompleto coletarDadosEdicao(FormularioCompleto formularioOriginal) {
        if (camposAtual == null) {
            mostrarAlerta("Erro", "Referências dos campos não encontradas. Usando dados originais.", Alert.AlertType.WARNING);
            return formularioOriginal;
        }
        
        try {
            // Criar uma cópia do formulário original para editar
            FormularioCompleto formularioEditado = new FormularioCompleto();
            formularioEditado.setId(formularioOriginal.getId());
            
            // Coletar dados da criança
            formularioEditado.setNomeCrianca(camposAtual.txtNome.getText());
            formularioEditado.setDataNascimento(camposAtual.dpDataNasc.getValue());
            formularioEditado.setRg(camposAtual.txtRg.getText());
            formularioEditado.setCpf(camposAtual.txtCpf.getText());
            formularioEditado.setDataEmissaoRg(camposAtual.dpEmissaoRg.getValue());
            formularioEditado.setOrgaoEmissorRg(camposAtual.txtOrgaoEmissor.getText());
            formularioEditado.setSexo(camposAtual.cbSexo.getValue());
            formularioEditado.setRaca(camposAtual.cbRaca.getValue());
            formularioEditado.setGemeo(camposAtual.cbGemeo.getValue());
            formularioEditado.setPossuiIrmaoNaCreche(camposAtual.cbIrmaoCreche.getValue());
            formularioEditado.setMunicipioNascimento(camposAtual.txtMunicipioNasc.getText());
            formularioEditado.setCartorioRegistro(camposAtual.txtCartorio.getText());
            formularioEditado.setMunicipioRegistro(camposAtual.txtMunicipioReg.getText());
            
            // Coletar dados de saúde
            formularioEditado.setCadastroNacionalSus(camposAtual.txtSus.getText());
            formularioEditado.setUnidadeSaude(camposAtual.txtUnidadeSaude.getText());
            formularioEditado.setProblemasSaude(camposAtual.cbProblemasSaude.getValue());
            formularioEditado.setMobilidade(camposAtual.cbMobilidade.getValue());
            
            // Coletar restrições alimentares
            Set<RestricaoAlimentar> restricoes = new HashSet<>();
            for (int i = 0; i < camposAtual.restricoesAlimentares.size(); i++) {
                if (camposAtual.restricoesAlimentares.get(i).isSelected()) {
                    restricoes.add(RestricaoAlimentar.values()[i]);
                }
            }
            formularioEditado.setRestricaoAlimentar(restricoes);
            
            // Coletar alergias
            Set<Alergias> alergiasSet = new HashSet<>();
            for (int i = 0; i < camposAtual.alergias.size(); i++) {
                if (camposAtual.alergias.get(i).isSelected()) {
                    alergiasSet.add(Alergias.values()[i]);
                }
            }
            formularioEditado.setAlergia(alergiasSet);
            
            // Coletar educação especial
            Set<EducacaoEspecial> educacaoSet = new HashSet<>();
            for (int i = 0; i < camposAtual.educacaoEspecial.size(); i++) {
                if (camposAtual.educacaoEspecial.get(i).isSelected()) {
                    educacaoSet.add(EducacaoEspecial.values()[i]);
                }
            }
            formularioEditado.setEducacaoEspecial(educacaoSet);
            
            // Coletar dados sociais
            formularioEditado.setRecebeBeneficioSocial(camposAtual.cbBeneficio.getValue());
            formularioEditado.setNis(camposAtual.txtNis.getText());
            
            try {
                if (!camposAtual.txtRendaFamiliar.getText().isEmpty()) {
                    formularioEditado.setRendaFamiliarMensal(new java.math.BigDecimal(camposAtual.txtRendaFamiliar.getText()));
                }
                if (!camposAtual.txtRendaPerCapita.getText().isEmpty()) {
                    formularioEditado.setRendaPerCapita(new java.math.BigDecimal(camposAtual.txtRendaPerCapita.getText()));
                }
            } catch (NumberFormatException e) {
                mostrarAlerta("Aviso", "Valores de renda inválidos, mantendo valores originais.", Alert.AlertType.WARNING);
                formularioEditado.setRendaFamiliarMensal(formularioOriginal.getRendaFamiliarMensal());
                formularioEditado.setRendaPerCapita(formularioOriginal.getRendaPerCapita());
            }
            
            // Coletar auxílios
            Set<Auxilio> auxiliosSet = new HashSet<>();
            for (int i = 0; i < camposAtual.auxilios.size(); i++) {
                if (camposAtual.auxilios.get(i).isSelected()) {
                    auxiliosSet.add(Auxilio.values()[i]);
                }
            }
            formularioEditado.setAuxilios(auxiliosSet);
            
            // Coletar dados de habitação
            try {
                if (!camposAtual.txtValorAluguel.getText().isEmpty()) {
                    formularioEditado.setValorAluguel(new java.math.BigDecimal(camposAtual.txtValorAluguel.getText()));
                }
                if (!camposAtual.txtNumComodos.getText().isEmpty()) {
                    formularioEditado.setNumeroComodos(Integer.parseInt(camposAtual.txtNumComodos.getText()));
                }
            } catch (NumberFormatException e) {
                mostrarAlerta("Aviso", "Valores numéricos inválidos, mantendo valores originais.", Alert.AlertType.WARNING);
                formularioEditado.setValorAluguel(formularioOriginal.getValorAluguel());
                formularioEditado.setNumeroComodos(formularioOriginal.getNumeroComodos());
            }
            
            formularioEditado.setSituacaoCasa(camposAtual.cbSituacaoCasa.getValue());
            formularioEditado.setTemFossa(camposAtual.cbFossa.getValue());
            formularioEditado.setTemCifon(camposAtual.cbCifon.getValue());
            formularioEditado.setTemEnergiaEletrica(camposAtual.cbEnergia.getValue());
            formularioEditado.setTemAguaEncanada(camposAtual.cbAgua.getValue());
            
            // Coletar dados escolares
            try {
                if (!camposAtual.txtSerie.getText().isEmpty()) {
                    formularioEditado.setSerieQueIraCursar(Integer.parseInt(camposAtual.txtSerie.getText()));
                }
                if (!camposAtual.txtAnoLetivo.getText().isEmpty()) {
                    formularioEditado.setAnoLetivo(Integer.parseInt(camposAtual.txtAnoLetivo.getText()));
                }
            } catch (NumberFormatException e) {
                mostrarAlerta("Aviso", "Valores de série/ano inválidos, mantendo valores originais.", Alert.AlertType.WARNING);
                formularioEditado.setSerieQueIraCursar(formularioOriginal.getSerieQueIraCursar());
                formularioEditado.setAnoLetivo(formularioOriginal.getAnoLetivo());
            }
            
            formularioEditado.setDataMatricula(camposAtual.dpMatricula.getValue());
            formularioEditado.setDataDesligamento(camposAtual.dpDesligamento.getValue());
            formularioEditado.setStatus(camposAtual.cbStatus.getValue());
            
            // Manter dados que não são editados nesta tela
            formularioEditado.setResponsavel(formularioOriginal.getResponsavel());
            formularioEditado.setComposicaoFamiliar(formularioOriginal.getComposicaoFamiliar());
            formularioEditado.setPessoasAutorizadas(formularioOriginal.getPessoasAutorizadas());
            
            return formularioEditado;
            
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao coletar dados editados: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
            return formularioOriginal;
        }
    }
    
    private void desabilitarEdicaoRecursiva(VBox container) {
        for (javafx.scene.Node node : container.getChildren()) {
            if (node instanceof TextField) {
                ((TextField) node).setEditable(false);
                node.setStyle(node.getStyle() + "; -fx-background-color: #f5f5f5;");
            } else if (node instanceof ComboBox) {
                ((ComboBox<?>) node).setDisable(true);
            } else if (node instanceof DatePicker) {
                ((DatePicker) node).setEditable(false);
                ((DatePicker) node).setDisable(true);
            } else if (node instanceof CheckBox) {
                ((CheckBox) node).setDisable(true);
            } else if (node instanceof VBox) {
                desabilitarEdicaoRecursiva((VBox) node);
            }
        }
    }
    
    private void carregarFormularios() {
        try {
            List<FormularioCompleto> lista = apiService.listarTodos();
            formularios.clear();
            formularios.addAll(lista);
        } catch (Exception e) {
            mostrarAlerta("Erro", "Erro ao carregar lista de formulários: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private FormularioCompleto selecionarFormulario(List<FormularioCompleto> formularios) {
        if (formularios.size() == 1) {
            return formularios.get(0);
        }
        
        // Criar um diálogo de seleção quando houver múltiplos formulários
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Múltiplos Formulários Encontrados");
        alert.setHeaderText("Foram encontrados " + formularios.size() + " formulários para este CPF.");
        alert.setContentText("Selecione qual formulário deseja abrir:");
        
        // Criar botões para cada formulário
        StringBuilder opcoes = new StringBuilder();
        for (int i = 0; i < formularios.size(); i++) {
            FormularioCompleto f = formularios.get(i);
            opcoes.append((i + 1)).append(". ").append(f.getNomeCrianca());
            if (f.getDataNascimento() != null) {
                opcoes.append(" (nascido em ").append(f.getDataNascimento()).append(")");
            }
            opcoes.append("\n");
        }
        
        alert.setContentText("Selecione qual formulário deseja abrir:\n\n" + opcoes.toString());
        
        // Criar input para seleção
        javafx.scene.control.TextInputDialog inputDialog = new javafx.scene.control.TextInputDialog("1");
        inputDialog.setTitle("Selecionar Formulário");
        inputDialog.setHeaderText("Múltiplos formulários encontrados para este CPF:");
        inputDialog.setContentText(opcoes.toString() + "\nDigite o número do formulário:");
        
        java.util.Optional<String> result = inputDialog.showAndWait();
        if (result.isPresent()) {
            try {
                int escolha = Integer.parseInt(result.get()) - 1;
                if (escolha >= 0 && escolha < formularios.size()) {
                    return formularios.get(escolha);
                } else {
                    mostrarAlerta("Erro", "Número inválido! Deve ser entre 1 e " + formularios.size(), Alert.AlertType.ERROR);
                }
            } catch (NumberFormatException e) {
                mostrarAlerta("Erro", "Por favor, digite um número válido!", Alert.AlertType.ERROR);
            }
        }
        
        return null; // Usuário cancelou ou erro na seleção
    }
    
    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}