/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LayslipTestModule } from '../../../test.module';
import { LayslipKeyHeaderComponent } from '../../../../../../main/webapp/app/entities/layslip-key-header/layslip-key-header.component';
import { LayslipKeyHeaderService } from '../../../../../../main/webapp/app/entities/layslip-key-header/layslip-key-header.service';
import { LayslipKeyHeader } from '../../../../../../main/webapp/app/entities/layslip-key-header/layslip-key-header.model';

describe('Component Tests', () => {

    describe('LayslipKeyHeader Management Component', () => {
        let comp: LayslipKeyHeaderComponent;
        let fixture: ComponentFixture<LayslipKeyHeaderComponent>;
        let service: LayslipKeyHeaderService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [LayslipTestModule],
                declarations: [LayslipKeyHeaderComponent],
                providers: [
                    LayslipKeyHeaderService
                ]
            })
            .overrideTemplate(LayslipKeyHeaderComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LayslipKeyHeaderComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LayslipKeyHeaderService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new LayslipKeyHeader(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.layslipKeyHeaders[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
