(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('electricrequirement', {
            parent: 'entity',
            url: '/electricrequirement',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.electricrequirement.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/electricrequirement/electricrequirements.html',
                    controller: 'ElectricrequirementController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('electricrequirement');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('electricrequirement-detail', {
            parent: 'electricrequirement',
            url: '/electricrequirement/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.electricrequirement.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/electricrequirement/electricrequirement-detail.html',
                    controller: 'ElectricrequirementDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('electricrequirement');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Electricrequirement', function($stateParams, Electricrequirement) {
                    return Electricrequirement.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'electricrequirement',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('electricrequirement-detail.edit', {
            parent: 'electricrequirement-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/electricrequirement/electricrequirement-dialog.html',
                    controller: 'ElectricrequirementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Electricrequirement', function(Electricrequirement) {
                            return Electricrequirement.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('electricrequirement.new', {
            parent: 'electricrequirement',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/electricrequirement/electricrequirement-dialog.html',
                    controller: 'ElectricrequirementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                temporaryrequired: null,
                                tem_load_existing: null,
                                tem_account_number: null,
                                temp_existing_load_demand_kw: null,
                                temp_existing_load_demand_kva: null,
                                temp_new_load_demand_kw: null,
                                temp_new_load_demand_kva: null,
                                temp_load_demand_date: null,
                                regular_load_required: null,
                                regular_existing_connection: null,
                                regular_account_number: null,
                                regular_existing_load_ifany_kw: null,
                                regular_existing_load_ifany_kva: null,
                                regular_new_load_demand_kw: null,
                                regular_new_load_demand_kva: null,
                                regular_load_demand_date: null,
                                customertype: null,
                                createdate: null,
                                updatedate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('electricrequirement', null, { reload: 'electricrequirement' });
                }, function() {
                    $state.go('electricrequirement');
                });
            }]
        })
        .state('electricrequirement.edit', {
            parent: 'electricrequirement',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/electricrequirement/electricrequirement-dialog.html',
                    controller: 'ElectricrequirementDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Electricrequirement', function(Electricrequirement) {
                            return Electricrequirement.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('electricrequirement', null, { reload: 'electricrequirement' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('electricrequirement.delete', {
            parent: 'electricrequirement',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/electricrequirement/electricrequirement-delete-dialog.html',
                    controller: 'ElectricrequirementDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Electricrequirement', function(Electricrequirement) {
                            return Electricrequirement.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('electricrequirement', null, { reload: 'electricrequirement' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
