CREATE OR REPLACE VIEW transaction_list
AS 
SELECT tr.id AS id,
       tr.description AS description,
       tr.date,
       tr.amount,
       tr.comment,
       acc.id AS accountId,
       acc.name AS accountName,
       toacc.id AS toAccountId,
       toacc.name AS toAccountName,
       ta.id AS tagId,
       ta.name AS tagName
  FROM transactions tr
       INNER JOIN accounts acc
           ON tr.account_id = acc.id
       INNER JOIN tags ta
           ON ta.id = tr.tag_id
       LEFT JOIN accounts toacc
           ON tr.to_account_id = toacc.id
;
