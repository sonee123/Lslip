/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LayslipTestModule } from '../../../test.module';
import { LayslipGridDetailsComponent } from '../../../../../../main/webapp/app/entities/layslip-grid-details/layslip-grid-details.component';
import { LayslipGridDetailsService } from '../../../../../../main/webapp/app/entities/layslip-grid-details/layslip-grid-details.service';
import { LayslipGridDetails } from '../../../../../../main/webapp/app/entities/layslip-grid-details/layslip-grid-details.model';

describe('Component Tests', () => {

    describe('LayslipGridDetails Management Component', () => {
        let comp: LayslipGridDetailsComponent;
        let fixture: ComponentFixture<LayslipGridDetailsComponent>;
        let service: LayslipGridDetailsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [LayslipGridDetailsComponent],
                providers: [
                    LayslipGridDetailsService
                ]
            })
            .overrideTemplate(LayslipGridDetailsComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LayslipGridDetailsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LayslipGridDetailsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new LayslipGridDetails(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.layslipGridDetails[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
