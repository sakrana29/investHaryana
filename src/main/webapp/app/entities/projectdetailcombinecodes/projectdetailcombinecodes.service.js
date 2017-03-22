(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectdetailcombinecodes', Projectdetailcombinecodes);

    Projectdetailcombinecodes.$inject = ['$resource'];

    function Projectdetailcombinecodes ($resource) {
        var resourceUrl =  'api/projectdetailcombinecodes/:id';

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
