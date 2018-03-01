/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LayslipTestModule } from '../../../test.module';
import { WorkCodeComponent } from '../../../../../../main/webapp/app/entities/work-code/work-code.component';
import { WorkCodeService } from '../../../../../../main/webapp/app/entities/work-code/work-code.service';
import { WorkCode } from '../../../../../../main/webapp/app/entities/work-code/work-code.model';

describe('Component Tests', () => {

    describe('WorkCode Management Component', () => {
        let comp: WorkCodeComponent;
        let fixture: ComponentFixture<WorkCodeComponent>;
        let service: WorkCodeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [WorkCodeComponent],
                providers: [
                    WorkCodeService
                ]
            })
            .overrideTemplate(WorkCodeComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WorkCodeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WorkCodeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new WorkCode(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.workCodes[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
