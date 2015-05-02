import csv
import logging as log
from time import strftime

def write(apiurl, filename, transactions):
    with open(filename, 'w', newline='') as csvfile:
        writer = csv.writer(csvfile, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)

        header = ['Created by sfinapp exporter. Exported from {0} at {1}'.format(apiurl, strftime('%Y-%m-%d %H:%M:%S'))]
        writer.writerow(header)
        header = ['Date','Description','Currency','Amount','Type','Tags','Account','Status']
        writer.writerow(header)

        log.info('Writing %d transactions to file', len(transactions))

        for tr in transactions:
            row = get_transaction_row(tr)
            log.debug('Converted transaction: %s', row)
            writer.writerow(row)

def get_transaction_row(tr):
    row = []
    row.append(tr['date'][:10]) # Only date
    row.append(tr['description'])
    row.append('HUF')

    amount = '{0:,.2f}'.format(abs(tr['amount'])).replace(',', ' ').replace('.', ',').replace(' ', '.')
    row.append('+ ' + amount)

    row.append(tr['type'])
    row.append(tr['tagNames'])

    acc = tr['accountName']
    if 'toAccountName' in tr:
        acc += ' -> ' + tr['toAccountName']
    row.append(acc)

    row.append('Cleared')

    return row
