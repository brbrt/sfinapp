describe('dateUtilSrv', function() {
    var service;

    beforeEach(function() {
        module('sfinapp.core.dateUtilSrv');

        inject(function(dateUtilSrv) {
            service = dateUtilSrv;
        });
    });


    it('is definied', function() {
        expect(service).toBeDefined();
    });

    it('parseDate -> formatDate', function() {
        var original = '2013-03-03';

        var date = service.parseDate(original);
        var result = service.formatDate(date);

        expect(result).toEqual(original);
    });

    it('formatDate -> parseDate', function() {
        var original = moment('2013-03-03', 'YYYY-MM-DD').toDate();

        var str = service.formatDate(original);
        var result = service.parseDate(str);

        expect(result).toEqual(original);
    });

    it('parseDates -> formatDates', function() {
        var original = {
            date: '2013-03-03',
            num: 674.1,
            notDate: 'this is not a date',
            a: null,
            child: {
                cDate: '2015-01-09',
                num: 983
            }
        };

        var obj = angular.copy(original);

        service.parseDates(obj);
        service.formatDates(obj);

        expect(obj).toEqual(original);
    });

    it('formatDates -> parseDates', function() {
        var original = {
            date: moment('2013-03-03', 'YYYY-MM-DD').toDate(),
            num: 674.1,
            notDate: 'this is not a date',
            a: null,
            child: {
                cDate: moment('2015-01-09', 'YYYY-MM-DD').toDate(),
                num: 983
            }
        };

        var obj = angular.copy(original);

        service.formatDates(obj);
        service.parseDates(obj);

        expect(obj).toEqual(original);
    });
});

