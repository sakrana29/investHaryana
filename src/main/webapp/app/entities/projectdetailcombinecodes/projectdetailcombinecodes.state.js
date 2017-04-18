(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectdetailcombinecodes', {
            parent: 'entity',
            url: '/projectdetailcombinecodes',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectdetailcombinecodes.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectdetailcombinecodes/projectdetailcombinecodes.html',
                    controller: 'ProjectdetailcombinecodesController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectdetailcombinecodes');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('projectdetailcombinecodes-detail', {
            parent: 'projectdetailcombinecodes',
            url: '/projectdetailcombinecodes/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.projectdetailcombinecodes.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projectdetailcombinecodes/projectdetailcombinecodes-detail.html',
                    controller: 'ProjectdetailcombinecodesDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projectdetailcombinecodes');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projectdetailcombinecodes', function($stateParams, Projectdetailcombinecodes) {
                    return Projectdetailcombinecodes.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projectdetailcombinecodes',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projectdetailcombinecodes-detail.edit', {
            parent: 'projectdetailcombinecodes-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectdetailcombinecodes/projectdetailcombinecodes-dialog.html',
                    controller: 'ProjectdetailcombinecodesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectdetailcombinecodes', function(Projectdetailcombinecodes) {
                            return Projectdetailcombinecodes.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectdetailcombinecodes.new', {
            parent: 'projectdetailcombinecodes',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectdetailcombinecodes/projectdetailcombinecodes-dialog.html',
                    controller: 'ProjectdetailcombinecodesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                investorid: null,
                                companydetailid: null,
                                projectsitedetailid: null,
                                projectfinanceid: null,
                                manufacturingid: null,
                                electricityrequirementid: null,
                                environmentimpactdetailid: null,
                                termdeclarationacceptid: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projectdetailcombinecodes', null, { reload: 'projectdetailcombinecodes' });
                }, function() {
                    $state.go('projectdetailcombinecodes');
                });
            }]
        })
        .state('projectdetailcombinecodes.edit', {
            parent: 'projectdetailcombinecodes',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectdetailcombinecodes/projectdetailcombinecodes-dialog.html',
                    controller: 'ProjectdetailcombinecodesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projectdetailcombinecodes', function(Projectdetailcombinecodes) {
                            return Projectdetailcombinecodes.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectdetailcombinecodes', null, { reload: 'projectdetailcombinecodes' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projectdetailcombinecodes.delete', {
            parent: 'projectdetailcombinecodes',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projectdetailcombinecodes/projectdetailcombinecodes-delete-dialog.html',
                    controller: 'ProjectdetailcombinecodesDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projectdetailcombinecodes', function(Projectdetailcombinecodes) {
                            return Projectdetailcombinecodes.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projectdetailcombinecodes', null, { reload: 'projectdetailcombinecodes' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
