(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('ProjectServicePaymentDetails', ProjectServicePaymentDetails);

    ProjectServicePaymentDetails.$inject = ['$resource', 'DateUtils'];

    function ProjectServicePaymentDetails ($resource, DateUtils) {
        var resourceUrl =  'api/project-service-payment-details/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.paymentDate = DateUtils.convertDateTimeFromServer(data.paymentDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
