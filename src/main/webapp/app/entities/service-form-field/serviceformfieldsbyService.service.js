(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('serviceformfielsbyService', serviceformfielsbyService);

    serviceformfielsbyService.$inject = ['$resource', 'DateUtils'];

    function serviceformfielsbyService ($resource, DateUtils) {
        var resourceUrl =  'api/servieformfield/service/:serviceid';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
//                        data.requireMarkedOnDate = DateUtils.convertDateTimeFromServer(data.requireMarkedOnDate);
//                        data.assigOnDate = DateUtils.convertDateTimeFromServer(data.assigOnDate);
//                        data.formFilledOnDate = DateUtils.convertDateTimeFromServer(data.formFilledOnDate);
//                        data.paymentMadeOnDate = DateUtils.convertDateTimeFromServer(data.paymentMadeOnDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
