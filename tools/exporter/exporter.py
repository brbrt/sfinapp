import argparse
import csv
import logging as log
import time

import csvwriter
import downloader


start_time = time.time()

argparser = argparse.ArgumentParser()
argparser.add_argument('-f', '--file', required=True, help='File to export.')
argparser.add_argument('-a', '--apiurl', required=True, help='The api url of the application from export.')
argparser.add_argument('-v', '--verbose', action='store_true', help='Enables verbose output.')
args = argparser.parse_args()

level = log.DEBUG if args.verbose else log.INFO
log.basicConfig(format='[%(asctime)s] [%(levelname)s] %(message)s', level=level)

filename = args.file
apiurl = args.apiurl

log.info('Exporting transactions from application "%s" to file "%s"', apiurl, filename)

try:
    transactions = downloader.download(apiurl)
    csvwriter.write(apiurl, filename, transactions)

except Exception as ex:
    log.error('Fatal error: %s', ex)
    # raise ex

elapsed = time.time() - start_time
log.info('Export finished in %.1f seconds.', elapsed)
