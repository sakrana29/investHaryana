(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('FileManagement', FileManagement);

    FileManagement.$inject = ['$resource'];

    function FileManagement ($resource) {
        var resourceUrl =  'api/FileManagement';

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
