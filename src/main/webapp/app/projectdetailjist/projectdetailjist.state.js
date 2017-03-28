(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('projectdetailjist', {
            parent: 'app',
            url: '/{id}/projectdetailjist',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/projectdetailjist/projectdetailjist.html',
                    controller: 'projectdetailjistController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('projectdetailjist');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projectcompletedetail', function($stateParams,Projectcompletedetail) {
                    return Projectcompletedetail.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('projectdetailjist-fillform', {
            parent: 'projectdetailjist',
            url: '/fillform/:serviceID?projectid',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/projectdetailjist/fillform.html',
                    controller: 'fillformController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg'

//                    resolve: {
//                        entity: function () {
//                            return {
//                                districtid: null,
//                                blockname: null,
//                                id: null
//                            };
//                        }
//                    }
                }).result.then(function() {
                    $state.go('projectdetailjist', null, { reload: 'projectdetailjist' });
                }, function() {
                    $state.go('projectdetailjist');
                });
            }]
        })
        .state('projectdetailjist-payfee', {
            parent: 'projectdetailjist',
            url: '/payfee/:serviceID?projectid',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/projectdetailjist/payfee.html',
                    controller: 'payfeeController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg'

//                    resolve: {
//                        entity: function () {
//                            return {
//                                districtid: null,
//                                blockname: null,
//                                id: null
//                            };
//                        }
//                    }
                }).result.then(function() {
                    $state.go('projectdetailjist', null, { reload: 'projectdetailjist' });
                }, function() {
                    $state.go('projectdetailjist');
                });
            }]
        });
    }
})();
