'use strict';

describe('Controller Tests', function() {

    describe('Modeofdisposalfor_discharge Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockModeofdisposalfor_discharge;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockModeofdisposalfor_discharge = jasmine.createSpy('MockModeofdisposalfor_discharge');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Modeofdisposalfor_discharge': MockModeofdisposalfor_discharge
            };
            createController = function() {
                $injector.get('$controller')("Modeofdisposalfor_dischargeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'investhryApp:modeofdisposalfor_dischargeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
