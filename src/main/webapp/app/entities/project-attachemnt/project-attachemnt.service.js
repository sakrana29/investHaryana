(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('ProjectAttachemnt', ProjectAttachemnt);

    ProjectAttachemnt.$inject = ['$resource'];

    function ProjectAttachemnt ($resource) {
        var resourceUrl =  'api/project-attachemnts/:id';

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
