(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Filetesting', Filetesting);

    Filetesting.$inject = ['$resource'];

    function Filetesting ($resource) {
        var resourceUrl =  'api/filetestings/:id';

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
