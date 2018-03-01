/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { LayslipTestModule } from '../../../test.module';
import { WorkCenterMasterDetailComponent } from '../../../../../../main/webapp/app/entities/work-center-master/work-center-master-detail.component';
import { WorkCenterMasterService } from '../../../../../../main/webapp/app/entities/work-center-master/work-center-master.service';
import { WorkCenterMaster } from '../../../../../../main/webapp/app/entities/work-center-master/work-center-master.model';

describe('Component Tests', () => {

    describe('WorkCenterMaster Management Detail Component', () => {
        let comp: WorkCenterMasterDetailComponent;
        let fixture: ComponentFixture<WorkCenterMasterDetailComponent>;
        let service: WorkCenterMasterService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [WorkCenterMasterDetailComponent],
                providers: [
                    WorkCenterMasterService
                ]
            })
            .overrideTemplate(WorkCenterMasterDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WorkCenterMasterDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WorkCenterMasterService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new WorkCenterMaster(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.workCenterMaster).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
