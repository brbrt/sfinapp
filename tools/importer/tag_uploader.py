import json
import logging as log

import requests

def upload(apiurl, transactions):
    tags = set()

    for tr in transactions:
        # Create a new set of the tags of the current transaction tr,
        # and add the elements of the new set to the 'tags' set.
        tags |= set(tr.tags)

    log.debug('All tags: %s', tags)

    url = apiurl + '/tags/'
    headers = {'Content-type': 'application/json'}

    for tag in tags:
        payload = {'name': tag, 'description': ''}

        log.info('Uploading tag: %s', payload)

        r = requests.post(url, data=json.dumps(payload), headers=headers)
        if r.status_code != 200:
            log.warn('Api error: %s', r.json())

    log.info('Tag uploading is done.')
