(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectcompletedetail', Projectcompletedetail);

    Projectcompletedetail.$inject = ['$resource'];

    function Projectcompletedetail ($resource) {
        var resourceUrl =  '/api/CompleteProjectDetail/:projectid';


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
