-- AlterTable
ALTER TABLE "answer" ADD COLUMN     "sequence" SERIAL NOT NULL;

-- AlterTable
ALTER TABLE "question" ADD COLUMN     "sequence" SERIAL NOT NULL;
