-- Script para verificar se os dados foram salvos no banco
-- Execute estas consultas no MySQL Workbench ou similar

-- Ver todas as crianças cadastradas
SELECT * FROM formulario_completo;

-- Ver responsáveis cadastrados
SELECT * FROM responsavel;

-- Ver endereços cadastrados
SELECT * FROM endereco;

-- Ver pessoas autorizadas
SELECT * FROM pessoas_autorizadas;

-- Ver composição familiar
SELECT * FROM composicao_familiar;

-- Consulta completa com JOINs para ver tudo junto
SELECT 
    f.nome_crianca,
    f.data_nascimento,
    f.cpf_crianca,
    r.nome as nome_responsavel,
    r.cpf as cpf_responsavel,
    e.logradouro,
    e.cidade
FROM formulario_completo f
LEFT JOIN responsavel r ON f.id = r.formulario_id
LEFT JOIN endereco e ON f.id = e.formulario_id;