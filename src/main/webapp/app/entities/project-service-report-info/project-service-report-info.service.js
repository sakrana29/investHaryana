(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('ProjectServiceReportInfo', ProjectServiceReportInfo);

    ProjectServiceReportInfo.$inject = ['$resource', 'DateUtils'];

    function ProjectServiceReportInfo ($resource, DateUtils) {
        var resourceUrl =  'api/project-service-report-infos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.assignDate = DateUtils.convertDateTimeFromServer(data.assignDate);
                        data.requireDate = DateUtils.convertDateTimeFromServer(data.requireDate);
                        data.finalActionDate = DateUtils.convertDateTimeFromServer(data.finalActionDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
