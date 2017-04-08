(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('ProjectServicePaymentDetailsByProjectAndService', ProjectServicePaymentDetailsByProjectAndService);

    ProjectServicePaymentDetailsByProjectAndService.$inject = ['$resource', 'DateUtils'];

    function ProjectServicePaymentDetailsByProjectAndService ($resource, DateUtils) {
        var resourceUrl =  'api/project-service-payment/projectandservice/:projectid/:serviceid';

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
