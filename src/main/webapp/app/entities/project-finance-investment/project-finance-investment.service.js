(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Project_finance_investment', Project_finance_investment);

    Project_finance_investment.$inject = ['$resource', 'DateUtils'];

    function Project_finance_investment ($resource, DateUtils) {
        var resourceUrl =  'api/project-finance-investments/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.project_construction_start_date = DateUtils.convertDateTimeFromServer(data.project_construction_start_date);
                        data.commercial_activity_start_date = DateUtils.convertDateTimeFromServer(data.commercial_activity_start_date);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
