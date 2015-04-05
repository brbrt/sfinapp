import argparse
import csv
import logging as log
import time

import parser
import transaction
import account_uploader
import tag_uploader
import transaction_uploader


start_time = time.time()

argparser = argparse.ArgumentParser()
argparser.add_argument('-f', '--file', required=True, help='File to import.')
argparser.add_argument('-a', '--apiurl', required=True, help='The api url of the application to import.')
argparser.add_argument('-v', '--verbose', action='store_true', help='Enables verbose output.')
args = argparser.parse_args()

level = log.DEBUG if args.verbose else log.INFO
log.basicConfig(format='[%(asctime)s] [%(levelname)s] %(message)s', level=level)

filename = args.file
apiurl = args.apiurl

log.info('Importing file: "%s" into application "%s"', filename, apiurl)

try:
    transactions = parser.parse(filename)

    account_uploader.upload(apiurl, transactions)
    tag_uploader.upload(apiurl, transactions)
    transaction_uploader.upload(apiurl, transactions)

except Exception as ex:
    log.error('Fatal error: %s', ex)

elapsed = time.time() - start_time
log.info('Import finished in %.1f seconds.', elapsed)
