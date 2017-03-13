(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('manufacturingdetail', {
            parent: 'entity',
            url: '/manufacturingdetail',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.manufacturingdetail.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/manufacturingdetail/manufacturingdetails.html',
                    controller: 'ManufacturingdetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('manufacturingdetail');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('manufacturingdetail-detail', {
            parent: 'manufacturingdetail',
            url: '/manufacturingdetail/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.manufacturingdetail.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/manufacturingdetail/manufacturingdetail-detail.html',
                    controller: 'ManufacturingdetailDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('manufacturingdetail');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Manufacturingdetail', function($stateParams, Manufacturingdetail) {
                    return Manufacturingdetail.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'manufacturingdetail',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('manufacturingdetail-detail.edit', {
            parent: 'manufacturingdetail-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manufacturingdetail/manufacturingdetail-dialog.html',
                    controller: 'ManufacturingdetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Manufacturingdetail', function(Manufacturingdetail) {
                            return Manufacturingdetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('manufacturingdetail.new', {
            parent: 'manufacturingdetail',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manufacturingdetail/manufacturingdetail-dialog.html',
                    controller: 'ManufacturingdetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                projectrawmaterialid: null,
                                productid: null,
                                processid: null,
                                manufacturing_flow_document: null,
                                manufacturing_flow_documentContentType: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('manufacturingdetail', null, { reload: 'manufacturingdetail' });
                }, function() {
                    $state.go('manufacturingdetail');
                });
            }]
        })
        .state('manufacturingdetail.edit', {
            parent: 'manufacturingdetail',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manufacturingdetail/manufacturingdetail-dialog.html',
                    controller: 'ManufacturingdetailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Manufacturingdetail', function(Manufacturingdetail) {
                            return Manufacturingdetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('manufacturingdetail', null, { reload: 'manufacturingdetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('manufacturingdetail.delete', {
            parent: 'manufacturingdetail',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/manufacturingdetail/manufacturingdetail-delete-dialog.html',
                    controller: 'ManufacturingdetailDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Manufacturingdetail', function(Manufacturingdetail) {
                            return Manufacturingdetail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('manufacturingdetail', null, { reload: 'manufacturingdetail' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
