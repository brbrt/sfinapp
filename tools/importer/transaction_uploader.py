import json
import logging as log

import requests

def upload(apiurl, transactions):
    r = requests.get(apiurl + '/accounts')
    accounts = r.json()
    log.debug('accounts: %s', accounts);

    r = requests.get(apiurl + '/tags')
    tags = r.json()
    log.debug('Tags: %s', tags);

    log.info('Converting transactions')

    for tr in transactions:
        log.debug('Converting transaction %s', tr);

        account_obj = next(a for a in accounts if a['name'] == tr.account)
        log.debug('Account %s, resolved account_obj %s', tr.account, account_obj)
        del(tr.account)
        tr.accountId = account_obj['id']

        if hasattr(tr, 'to_account'):
            to_account_obj = next(a for a in accounts if a['name'] == tr.to_account)
            log.debug('ToAccount %s, resolved to_account_obj %s', tr.to_account, to_account_obj)
            del(tr.to_account)
            tr.toAccountId = to_account_obj['id']

        tag_objs = []
        for tr_tag in tr.tags:
            tag_obj = next(t for t in tags if t['name'] == tr_tag)
            tag_objs.append(tag_obj)

        log.debug('Tags %s, resolved tag_objs %s', tr.tags, tag_objs)
        del(tr.tags)
        tr.tagIds = [to['id'] for to in tag_objs]

        tr.date += 'T00:00:00'

    log.info('Uploading transactions')

    url = apiurl + '/transactions/batch'
    headers = {'Content-type': 'application/json'}

    request = '[%s]' % ',\n'.join([tr.__str__() for tr in reversed(transactions)])

    log.debug('Request payload: %s', request)

    r = requests.post(url, data=request, headers=headers)
    if r.status_code != 200:
        log.error('Api error: %s', r.json())
        raise Exception('Api error')

    log.info('Transaction uploading is done.')
