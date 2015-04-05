import csv
import logging as log

from transaction import *

def parse(filename):
    with open(filename, 'r') as f:
        reader = csv.reader(f)
        rows = list(reader)

    log.debug('Processing %d rows.', len(rows))

    transactions = []

    for i, row in enumerate(rows):
        if len(row) == 0:
             continue

        try:
            tr = Transaction(row)
            transactions.append(tr)
            log.debug(tr)
        except ParseError as e:
            log.warn('ParseError: "%s", row %s is skipped.', e, i)

    return transactions
