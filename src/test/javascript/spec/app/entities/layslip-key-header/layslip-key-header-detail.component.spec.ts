/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { LayslipTestModule } from '../../../test.module';
import { LayslipKeyHeaderDetailComponent } from '../../../../../../main/webapp/app/entities/layslip-key-header/layslip-key-header-detail.component';
import { LayslipKeyHeaderService } from '../../../../../../main/webapp/app/entities/layslip-key-header/layslip-key-header.service';
import { LayslipKeyHeader } from '../../../../../../main/webapp/app/entities/layslip-key-header/layslip-key-header.model';

describe('Component Tests', () => {

    describe('LayslipKeyHeader Management Detail Component', () => {
        let comp: LayslipKeyHeaderDetailComponent;
        let fixture: ComponentFixture<LayslipKeyHeaderDetailComponent>;
        let service: LayslipKeyHeaderService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [LayslipKeyHeaderDetailComponent],
                providers: [
                    LayslipKeyHeaderService
                ]
            })
            .overrideTemplate(LayslipKeyHeaderDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LayslipKeyHeaderDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LayslipKeyHeaderService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new LayslipKeyHeader(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.layslipKeyHeader).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
