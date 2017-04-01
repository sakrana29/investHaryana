(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Project_phase', Project_phase);

    Project_phase.$inject = ['$resource', 'DateUtils'];

    function Project_phase ($resource, DateUtils) {
        var resourceUrl =  'api/project-phases/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.implementationdate = DateUtils.convertDateTimeFromServer(data.implementationdate);
                        data.createdate = DateUtils.convertDateTimeFromServer(data.createdate);
                        data.updatedate = DateUtils.convertDateTimeFromServer(data.updatedate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
