(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('project-finance-investment', {
            parent: 'entity',
            url: '/project-finance-investment',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.project_finance_investment.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project-finance-investment/project-finance-investments.html',
                    controller: 'Project_finance_investmentController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('project_finance_investment');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('project-finance-investment-detail', {
            parent: 'project-finance-investment',
            url: '/project-finance-investment/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'investhryApp.project_finance_investment.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/project-finance-investment/project-finance-investment-detail.html',
                    controller: 'Project_finance_investmentDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('project_finance_investment');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Project_finance_investment', function($stateParams, Project_finance_investment) {
                    return Project_finance_investment.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'project-finance-investment',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('project-finance-investment-detail.edit', {
            parent: 'project-finance-investment-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-finance-investment/project-finance-investment-dialog.html',
                    controller: 'Project_finance_investmentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Project_finance_investment', function(Project_finance_investment) {
                            return Project_finance_investment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project-finance-investment.new', {
            parent: 'project-finance-investment',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-finance-investment/project-finance-investment-dialog.html',
                    controller: 'Project_finance_investmentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                projectid: null,
                                land_cost: null,
                                building_cost: null,
                                machinery_cost: null,
                                misc_assests: null,
                                total_project_cost: null,
                                isfdi: null,
                                fdivalue: null,
                                fdi_country: null,
                                foreign_funding_source: null,
                                project_construction_start_date: null,
                                commercial_activity_start_date: null,
                                proposedproject_scheduleid: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('project-finance-investment', null, { reload: 'project-finance-investment' });
                }, function() {
                    $state.go('project-finance-investment');
                });
            }]
        })
        .state('project-finance-investment.edit', {
            parent: 'project-finance-investment',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-finance-investment/project-finance-investment-dialog.html',
                    controller: 'Project_finance_investmentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Project_finance_investment', function(Project_finance_investment) {
                            return Project_finance_investment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-finance-investment', null, { reload: 'project-finance-investment' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('project-finance-investment.delete', {
            parent: 'project-finance-investment',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/project-finance-investment/project-finance-investment-delete-dialog.html',
                    controller: 'Project_finance_investmentDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Project_finance_investment', function(Project_finance_investment) {
                            return Project_finance_investment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-finance-investment', null, { reload: 'project-finance-investment' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
