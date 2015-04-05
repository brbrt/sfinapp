import json
import logging as log

import requests

def upload(apiurl, transactions):
    accounts = set()

    for tr in transactions:
        accounts.add(tr.account)
        if hasattr(tr, 'to_account'):
            accounts.add(tr.to_account)

    log.debug('All accounts: %s', accounts)

    url = apiurl + '/accounts/'
    headers = {'Content-type': 'application/json'}

    for account in accounts:
        payload = {'name': account, 'description': ''}

        log.info('Uploading account: %s', payload)

        r = requests.post(url, data=json.dumps(payload), headers=headers)
        if r.status_code != 200:
            log.warn('Api error: %s', r.json())

    log.info('Account uploading is done.')
