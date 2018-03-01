import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { LayslipGridDetails } from './layslip-grid-details.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<LayslipGridDetails>;

@Injectable()
export class LayslipGridDetailsService {

    private resourceUrl =  SERVER_API_URL + 'api/layslip-grid-details';

    constructor(private http: HttpClient) { }

    create(layslipGridDetails: LayslipGridDetails): Observable<EntityResponseType> {
        const copy = this.convert(layslipGridDetails);
        return this.http.post<LayslipGridDetails>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(layslipGridDetails: LayslipGridDetails): Observable<EntityResponseType> {
        const copy = this.convert(layslipGridDetails);
        return this.http.put<LayslipGridDetails>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<LayslipGridDetails>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<LayslipGridDetails[]>> {
        const options = createRequestOption(req);
        return this.http.get<LayslipGridDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<LayslipGridDetails[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: LayslipGridDetails = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<LayslipGridDetails[]>): HttpResponse<LayslipGridDetails[]> {
        const jsonResponse: LayslipGridDetails[] = res.body;
        const body: LayslipGridDetails[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to LayslipGridDetails.
     */
    private convertItemFromServer(layslipGridDetails: LayslipGridDetails): LayslipGridDetails {
        const copy: LayslipGridDetails = Object.assign({}, layslipGridDetails);
        return copy;
    }

    /**
     * Convert a LayslipGridDetails to a JSON which can be sent to the server.
     */
    private convert(layslipGridDetails: LayslipGridDetails): LayslipGridDetails {
        const copy: LayslipGridDetails = Object.assign({}, layslipGridDetails);
        return copy;
    }
}
