(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Payment', Payment);

    Payment.$inject = ['$resource'];

    function Payment ($resource) {
        var resourceUrl =  'api/PaymentParameters/:projectid/:amount';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
