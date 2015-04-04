import argparse
import csv
import logging as log

from transaction import *

log.basicConfig(format='[%(asctime)s] [%(levelname)s] %(message)s', level=log.DEBUG)

parser = argparse.ArgumentParser()
parser.add_argument('--file', required=True, help='File to import.',)
args = parser.parse_args()

filename = args.file

log.info('Importing file: "%s"', filename)

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
        log.info('ParseError: "%s", row %s is skipped.', e, i)


tags = set()

for tr in transactions:
    # Create a new set of the tags of the current transaction tr,
    # and add the elements of the new set to the 'tags' set.
    tags |= set(tr.tags)

log.debug('All tags: %s', tags)


log.info('Import finished.')
