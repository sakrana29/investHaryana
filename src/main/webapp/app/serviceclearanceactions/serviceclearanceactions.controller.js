(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('serviceClearanceActionController', serviceClearanceActionController);

    serviceClearanceActionController.$inject = ['$timeout', '$scope', '$stateParams','$state','projectServiceReportInfo', 'Projectcompletedetail', 'Auth', 'Principal', 'DepartmentService', 'Projectservicedetail','ProjectservicedetailbyProject','ProjectServiceReportInfo'];

    function serviceClearanceActionController ($timeout, $scope, $stateParams, $state, projectServiceReportInfo, Projectcompletedetail, Auth, Principal, DepartmentService, Projectservicedetail,ProjectservicedetailbyProject,ProjectServiceReportInfo) {
        var vm = this;
        vm.projectServiceReportInfo=projectServiceReportInfo;

        loadProjectServices();
        function loadProjectServices()
        {
            vm.projectservicelist=ProjectservicedetailbyProject.query({id : $stateParams.id});
        }

        Principal.identity().then(function(account) {
        vm.account = account;
        });

        vm.markRequired=markRequired;
        function markRequired(projectservice)
        {
            projectservice.isRequired=true;
            Projectservicedetail.save(projectservice,onMarkSuccess,onMarkError);
        }
        function onMarkSuccess(result)
        {
            $scope.$emit('investhryApp:projectservicedetailUpdate', result);
            vm.projectServiceReportInfo.projectid=result.projectid;
            vm.projectServiceReportInfo.departmentname=result.departmentName;
            vm.projectServiceReportInfo.servicename=result.serviceName;
            vm.projectServiceReportInfo.requireDate=result.requireMarkedOnDate;
            vm.projectServiceReportInfo.status=result.status;
            vm.projectServiceReportInfo.stage=result.serviceStage;
            ProjectServiceReportInfo.save(vm.projectServiceReportInfo, onSaveSuccess, onSaveError);
            loadProjectServices();
        }
        function onSaveSuccess(result)
        {
            $scope.$emit('investhryApp:projectServiceReportInfoUpdate', result);
        }
        function onMarkError()
        {

        }
        function onSaveError()
        {}
    }
})();
