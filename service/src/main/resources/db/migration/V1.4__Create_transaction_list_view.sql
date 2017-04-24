CREATE OR REPLACE VIEW overview
AS 
SELECT tr.id AS transaction_id,
       tr.description AS description,
       tr.date,
       tr.amount,
       tr.comment,
       acc.id AS account_id,
       acc.name AS account_name,
       toacc.id AS to_account_id,
       toacc.name AS to_account_name,
       ta.id AS tag_id,
       ta.name AS tag_name
  FROM transactions tr
       INNER JOIN accounts acc
           ON tr.account_id = acc.id
       INNER JOIN tags ta
           ON ta.id = tr.tag_id
       LEFT JOIN accounts toacc
           ON tr.to_account_id = toacc.id
;
