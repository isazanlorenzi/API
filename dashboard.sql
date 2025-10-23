CREATE TABLE colaboradores (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  area VARCHAR(100),
  gerente_id INT REFERENCES colaboradores(id), -- auto-relacionamento (gerente)
  ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE metas (
  id SERIAL PRIMARY KEY,
  colaborador_id INT NOT NULL REFERENCES colaboradores(id) ON DELETE CASCADE,
  mes DATE NOT NULL, -- ideal para indicar o mês (pode ser o primeiro dia do mês, por exemplo)
  meta DECIMAL(10,2) NOT NULL,
  atingido DECIMAL(10,2) DEFAULT 0,
  data_atualizacao TIMESTAMP DEFAULT NOW()
);
