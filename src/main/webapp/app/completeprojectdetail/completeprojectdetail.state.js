(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('completeprojectdetail', {
            parent: 'app',
            url: '/completeprojectdetail{id}',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/completeprojectdetail/completeprojectdetail.html',
                    controller: 'completeprojectdetailController',
                    controllerAs: 'vm'
                }
            },
        });
    }
})();
