import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity()
export class Registro {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column()
  fecha!: Date;

  @Column('int')
  temperatura!: number;
}
