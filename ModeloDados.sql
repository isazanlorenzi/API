CREATE TABLE colaboradores (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100),
  area VARCHAR(100),
  gerente_id INT,
  ativo BOOLEAN DEFAULT true
);

CREATE TABLE metas (
  id SERIAL PRIMARY KEY,
  colaborador_id INT REFERENCES colaboradores(id),
  mes DATE,
  meta DECIMAL(10,2),
  atingido DECIMAL(10,2),
  data_atualizacao TIMESTAMP DEFAULT NOW()
);