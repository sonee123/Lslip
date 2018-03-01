import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { WorkCode } from './work-code.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<WorkCode>;

@Injectable()
export class WorkCodeService {

    private resourceUrl =  SERVER_API_URL + 'api/work-codes';

    constructor(private http: HttpClient) { }

    create(workCode: WorkCode): Observable<EntityResponseType> {
        const copy = this.convert(workCode);
        return this.http.post<WorkCode>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(workCode: WorkCode): Observable<EntityResponseType> {
        const copy = this.convert(workCode);
        return this.http.put<WorkCode>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<WorkCode>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<WorkCode[]>> {
        const options = createRequestOption(req);
        return this.http.get<WorkCode[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<WorkCode[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: WorkCode = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<WorkCode[]>): HttpResponse<WorkCode[]> {
        const jsonResponse: WorkCode[] = res.body;
        const body: WorkCode[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to WorkCode.
     */
    private convertItemFromServer(workCode: WorkCode): WorkCode {
        const copy: WorkCode = Object.assign({}, workCode);
        return copy;
    }

    /**
     * Convert a WorkCode to a JSON which can be sent to the server.
     */
    private convert(workCode: WorkCode): WorkCode {
        const copy: WorkCode = Object.assign({}, workCode);
        return copy;
    }
}
