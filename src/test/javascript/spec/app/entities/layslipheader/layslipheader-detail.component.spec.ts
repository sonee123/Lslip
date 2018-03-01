/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { LayslipTestModule } from '../../../test.module';
import { LayslipheaderDetailComponent } from '../../../../../../main/webapp/app/entities/layslipheader/layslipheader-detail.component';
import { LayslipheaderService } from '../../../../../../main/webapp/app/entities/layslipheader/layslipheader.service';
import { Layslipheader } from '../../../../../../main/webapp/app/entities/layslipheader/layslipheader.model';

describe('Component Tests', () => {

    describe('Layslipheader Management Detail Component', () => {
        let comp: LayslipheaderDetailComponent;
        let fixture: ComponentFixture<LayslipheaderDetailComponent>;
        let service: LayslipheaderService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [LayslipheaderDetailComponent],
                providers: [
                    LayslipheaderService
                ]
            })
            .overrideTemplate(LayslipheaderDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LayslipheaderDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LayslipheaderService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Layslipheader(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.layslipheader).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
