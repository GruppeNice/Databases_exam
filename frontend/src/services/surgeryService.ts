import api from './api';
import { Surgery, SurgeryRequest } from '../types';

export const surgeryService = {
  getAll: async (): Promise<Surgery[]> => {
    const response = await api.get<Surgery[]>('/surgeries/all');
    // Handle 204 NO_CONTENT response
    if (response.status === 204 || !response.data) {
      return [];
    }
    return response.data;
  },

  getById: async (id: string): Promise<Surgery> => {
    const response = await api.get<Surgery>(`/surgeries/${id}`);
    return response.data;
  },

  create: async (data: SurgeryRequest): Promise<Surgery> => {
    const response = await api.post<Surgery>('/surgeries/create', data);
    return response.data;
  },

  update: async (id: string, data: SurgeryRequest): Promise<Surgery> => {
    const response = await api.put<Surgery>(`/surgeries/update/${id}`, data);
    return response.data;
  },

  delete: async (id: string): Promise<void> => {
    await api.delete(`/surgeries/delete/${id}`);
  }
};

