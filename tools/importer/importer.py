import argparse
import csv
import logging as log

import parser
import transaction

log.basicConfig(format='[%(asctime)s] [%(levelname)s] %(message)s', level=log.DEBUG)

argparser = argparse.ArgumentParser()
argparser.add_argument('--file', required=True, help='File to import.',)
args = argparser.parse_args()

filename = args.file

log.info('Importing file: "%s"', filename)

transactions = parser.parse(filename)

log.info('Import finished.')
