(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('ProjectServiceLog', ProjectServiceLog);

    ProjectServiceLog.$inject = ['$resource', 'DateUtils'];

    function ProjectServiceLog ($resource, DateUtils) {
        var resourceUrl =  'api/project-service-logs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.commentDate = DateUtils.convertDateTimeFromServer(data.commentDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
