import json
import logging as log

import requests

def download(apiurl):
    url = apiurl + '/transactions/'

    log.info('Downloading all transactions from: %s', url)

    r = requests.get(url)
    if r.status_code != 200:
        log.warn('Api error: %s', r.json())
        return []

    return r.json()
