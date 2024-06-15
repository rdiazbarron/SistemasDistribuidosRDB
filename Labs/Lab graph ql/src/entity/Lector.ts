import { Entity, PrimaryGeneratedColumn, Column, ManyToMany, JoinTable } from 'typeorm';
import { Libro } from './Libro';

@Entity()
export class Lector {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column()
  nombre!: string;

  @ManyToMany(() => Libro)
  @JoinTable()
  librosPrestados!: Libro[];
}
