(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectcompletedetaildata', Projectcompletedetaildata);

    Projectcompletedetaildata.$inject = ['$resource'];

    function Projectcompletedetaildata ($resource) {
        var resourceUrl =  '/api/CompleteProjectDetailData/:id';


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
