import json
import re

class ParseError(Exception):
    def __init__(self, message):
        self.message = message


class Transaction():
    def __init__(self, list):
        length = len(list)
        if length < 7:
            raise ParseError('Invalid column count: {0}'.format(length))

        date = list.pop(0)
        if not re.match(r'\d{4}-\d{2}-\d{2}', date):
            raise ParseError('Invalid date column "{0}"'.format(date))

        #self.date = time.strptime(list[0], '%Y-%m-%d')
        self.date = date

        self.description = list.pop(0)

        # Currency doesn't matter
        list.pop(0)

        numstr = list.pop(0).replace('.', '').replace(',', '.')[2:]
        self.amount = int(float(numstr))

        self.type = list.pop(0)

        self.tags = list.pop(0).split(',')

        self.account = list.pop(0)

        if self.type == 'Transfer':
            parts = self.account.split('->')
            self.account = parts[0].strip()
            self.to_account = parts[1].strip()

    def __str__(self):
        return json.dumps(self, default=lambda o: o.__dict__)
