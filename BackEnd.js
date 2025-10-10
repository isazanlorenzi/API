// Exemplo em Node.js (Express)
app.get('/dashboard/atingimento/:gerenteId', async (req, res) => {
  const gerenteId = req.params.gerenteId;

  const query = `
    SELECT c.nome,
           c.area,
           m.mes,
           m.meta,
           m.atingido,
           ROUND((m.atingido / m.meta) * 100, 2) AS percentual_atingimento
    FROM colaboradores c
    JOIN metas m ON c.id = m.colaborador_id
    WHERE c.gerente_id = $1
    ORDER BY m.mes DESC;
  `;

  const { rows } = await db.query(query, [gerenteId]);
  res.json(rows);
});